import { jwtDecode } from 'jwt-decode';
import NextAuth from 'next-auth';
import { JWT } from 'next-auth/jwt';
import CredentialsProvider from 'next-auth/providers/credentials';

import { get, post } from '@/services/api';
import { API } from '@/settings/api';

import type { NextAuthConfig } from 'next-auth';

import type { UserDto } from '@/types/api/data-types';
import type { Session } from 'next-auth';
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

                console.log('AUTHORIZE', { email, password });

                const userTokens = await post<{ accessToken: string; refreshToken: string }>(`/${API.LOGIN}`, { email, password });

                if (!userTokens) {
                    console.error("Login failed");
                    return null;
                }

                return {
                    accessToken: userTokens.accessToken,
                    refreshToken: userTokens.refreshToken
                }
            },
        }),
    ],

    callbacks: {
        async jwt({ token, user, account }) {
            console.log('JWT', { token, user, account });


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
            } catch (error) {
                console.error('Error refreshing token:', error);
                return { ...token, error: 'RefreshAccessTokenError' };
            }

        },

        async session({ session, token }: { session: Session; token: JWT }) {
            console.log("SESSION", { session, token });

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

            session.user = {
                ...user,
                ...token.user
            }

            return session;
        },
    },
};


export const { handlers, signIn, signOut, auth } = NextAuth(authOptions);