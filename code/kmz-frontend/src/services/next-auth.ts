import NextAuth from 'next-auth';
import { JWT } from 'next-auth/jwt';
import CredentialsProvider from 'next-auth/providers/credentials';

import { get } from '@/services/api';
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

                console.log('Authorize', { email, password });

                const response = await fetch(`${API.ENDPOINT}/auth/login`, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({ email, password }),
                });

                if (!response.ok) {
                    console.error("Login failed");
                    return null;
                }

                const data = await response.json();

                return {
                    token: data.token
                }
            },
        }),
    ],

    callbacks: {
        async jwt({ token, user, account }) {
            console.log('jwt', { token, user, account });

            if (user) token.accessToken = user.token
            return token
        },

        async session({ session, token }: { session: Session; token: JWT }) {
            console.log("session", { session, token });

            const systemResponse = await get<{ superAdminExists: boolean }>(`${API.SYSTEM_STATUS}`)

            if (!systemResponse.superAdminExists)
                return session;

            const user = await get<UserDto>(`${API.ENDPOINT}/auth/me`, {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token.accessToken}`,
                },
            });

            if (!user) {
                console.error("Login failed");
                return session;
            }

            session.user = user;

            return session;
        },
    },
};


export const { handlers, signIn, signOut, auth } = NextAuth(authOptions);