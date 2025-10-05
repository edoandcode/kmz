import type { DefaultSession, DefaultUser } from 'next-auth';
import { UserDto } from '../api/data-types';

// 👇 Converts this file into an ES module to prevent it from being treated as a global script.
// This avoids conflicts with global declarations.
export { }

// Extend the "next-auth" module to add custom properties to the Session interface
declare module "next-auth" {
    interface User extends DefaultUser {
        token: string
    }

    interface Session extends DefaultSession {
        user: UserDto
        accessToken?: string
    }
}

// Extend the "next-auth/jwt" module to add custom properties to the JWT interface
declare module "next-auth/jwt" {
    interface JWT {
        // Optional property to include a JWT token inside the JWT payload itself.
        jwt?: string

        // Optional user identifier included in the JWT payload.
        // Can be used to easily identify the user during token validation or session creation.
        id?: string | number
    }
}
