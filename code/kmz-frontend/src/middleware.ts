import { stackMiddleware } from '@edoandcode/utils-next/middleware';

import superAdminSetupMiddleware from '@/middleware/superAdminSetup';
import { ROUTES } from '@/settings/routes';

import authMiddleware from './middleware/auth';

import type { MiddlewareItem } from '@edoandcode/utils-next/middleware';

const middlewareStack = [
    {
        matcher: /^(.*)$/,
        fn: superAdminSetupMiddleware
    },
    {
        matcher: function (pathname) {
            const publicRoutes = [ROUTES.LOGIN, ROUTES.SIGNUP_SUPERADMIN, ROUTES.SIGNUP, ROUTES.HOME];
            return (!publicRoutes.includes(pathname.replace('/', '')) && pathname !== '/') && !pathname.startsWith('/api/auth');
        },
        fn: authMiddleware
    }
] as MiddlewareItem[];


export const middleware = stackMiddleware(middlewareStack);

export const config = {
    matcher: ['/((?!_next|favicon\\.ico).*)'],
};