import { NextResponse } from 'next/server';

import { ROUTES } from '@/settings/routes';

import authProxy from './middleware/auth';

import type { NextRequest } from 'next/server';

export async function proxy(req: NextRequest) {
    const { pathname } = req.nextUrl;

    // Skip Next.js internals and static assets
    if (pathname.startsWith('/_next') || pathname === '/favicon.ico') {
        return NextResponse.next();
    }

    // Public routes that don't require authentication
    const publicRoutes = [ROUTES.LOGIN, ROUTES.SIGNUP_SUPERADMIN, ROUTES.SIGNUP, ROUTES.HOME];

    const isPublicRoute =
        (pathname === '/' || publicRoutes.includes(pathname.replace('/', ''))) ||
        pathname.startsWith('/api/auth');

    let res: NextResponse | undefined;


    if (!pathname.startsWith('/api/auth')) {
        // Apply auth middleware only on non-public routes
        if (!isPublicRoute) {
            res = await authProxy();
        }
    }

    // Continue request if no middleware returned a response
    return res || NextResponse.next();
}

export const config = {
    matcher: ['/((?!_next|favicon\\.ico).*)'],
};
