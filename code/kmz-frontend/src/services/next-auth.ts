import NextAuth from 'next-auth';
import CredentialsProvider from 'next-auth/providers/credentials';

import { API } from '@/settings/api';

import type { NextAuthConfig } from 'next-auth';


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

        async session({ session, token }) {
            console.log("session", { session, token });


            const response = await fetch(`${API.ENDPOINT}/auth/me`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token.accessToken}`,
                },
            });

            if (!response.ok) {
                console.error("Login failed");
                return session;
            }

            const data = await response.json();

            session.user = data;

            return session;
        },
    },
};


export const { handlers, signIn, signOut, auth } = NextAuth(authOptions);