import { jwtDecode } from 'jwt-decode';
import NextAuth from 'next-auth';
import { JWT } from 'next-auth/jwt';
import CredentialsProvider from 'next-auth/providers/credentials';

import { get, post } from '@/services/api';
import { API } from '@/settings/api';

import type { NextAuthConfig } from 'next-auth';

import type { UserDto } from "@/types/api/user/types";
import type { Session } from 'next-auth';

import type { ApiError } from '@/types/next-auth/next-auth';

export const authOptions: NextAuthConfig = {
    session: { strategy: "jwt" },

    providers: [
        CredentialsProvider({
            name: "Credentials",
            credentials: {
                email: { label: "Email", type: "email" },
                password: { label: "Password", type: "password" },
            },
            async authorize(credentials) {
                const { email, password } = credentials;

                //  console.log('AUTHORIZE', { email, password });

                try {
                    const userTokens = await post<{ accessToken: string; refreshToken: string }>(`/${API.LOGIN}`, { email, password });

                    if (!userTokens) {
                        console.error("Login failed");
                        return null;
                    }

                    return {
                        accessToken: userTokens.accessToken,
                        refreshToken: userTokens.refreshToken
                    }
                } catch (err) {
                    const error: ApiError =
                        err instanceof Error ? err : new Error('Unexpected error during login');

                    console.error('[Authorize Error]', {
                        message: error.message,
                        status: error.status,
                        details: error.details,
                    });

                    // Handle invalid credentials (backend 400 or 401)
                    if (error.status === 400 || error.status === 401) {
                        console.warn('Invalid credentials');
                        return null; // NextAuth will automatically show "CredentialsSignin"
                    }

                    // Handle network error or unreachable server
                    if (error instanceof TypeError) {
                        throw new Error('Unable to reach authentication server');
                    }

                    // Handle any other unexpected error, preserving detailed message
                    throw new Error(error.message || 'Unexpected error during login');
                }
            },
        }),
    ],

    callbacks: {
        async jwt({ token, user, account }) {
            // console.log('JWT', { token, user, account });


            let decodedAccessToken

            if (user && account) {
                decodedAccessToken = jwtDecode<{ exp: number, iat: number }>(user.accessToken);
                return ({
                    user: {
                        accessToken: user.accessToken,
                        refreshToken: user.refreshToken,
                        iat: decodedAccessToken?.iat,
                        exp: decodedAccessToken?.exp,
                    }
                })
            }

            decodedAccessToken = jwtDecode<{ exp: number, iat: number }>(token.user.accessToken);

            if (decodedAccessToken.exp * 1000 > Date.now())
                return token


            // here I should implement refresh token logic
            try {

                if (!token.user.refreshToken)
                    throw new Error('No refresh token available');

                console.log('REFRESHING TOKEN', token.user.refreshToken);

                const data = await post<{ accessToken: string }>('/auth/refresh', { refreshToken: token.user.refreshToken });

                console.log('REFRESHED TOKEN', data);

                decodedAccessToken = jwtDecode<{ exp: number, iat: number }>(data.accessToken);

                // return new token object preserving user info from previous token
                return {
                    user: {
                        ...token.user,
                        accessToken: data.accessToken,
                        refreshToken: token.user.refreshToken,
                        iat: decodedAccessToken?.iat,
                        exp: decodedAccessToken?.exp,
                    }
                };
            } catch (err: unknown) {
                console.error('Error refreshing token:', err);

                const error: ApiError = err instanceof Error ? err : new Error('Unknown error');

                console.error('[JWT Refresh Error]', {
                    message: error.message,
                    status: error.status,
                    details: error.details,
                });

                return {
                    ...token,
                    error: {
                        type: 'RefreshAccessTokenError' as const,
                        message: error.message || 'Failed to refresh access token',
                        status: error.status ?? null,
                        details: error.details ?? null,
                    },
                };
            }

        },

        async session({ session, token }: { session: Session; token: JWT }) {
            // console.log("SESSION", { session, token });

            const systemResponse = await get<{ superAdminExists: boolean }>(`${API.SYSTEM_STATUS}`)

            if (!systemResponse.superAdminExists)
                return session;

            const user = await get<UserDto>(`/auth/me`, {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token.user.accessToken}`,
                },
            });


            if (!user) {
                console.error("Login failed");
                return session;
            }

            const { refreshToken, ...rest } = token.user


            session.user = {
                ...user,
                ...rest
            }

            return session;
        },
    },
    trustHost: true

};


export const { handlers, signIn, signOut, auth } = NextAuth(authOptions);