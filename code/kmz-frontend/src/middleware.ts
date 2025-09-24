import { stackMiddleware } from "@edoandcode/utils-next/middleware";
import type { MiddlewareItem } from "@edoandcode/utils-next/middleware";
import superAdminSetup from "@/middleware/superAdminSetup";


const middlewareStack = [
    {
        matcher: /^(.*)$/,
        fn: superAdminSetup
    }
] as MiddlewareItem[];


export const middleware = stackMiddleware(middlewareStack);

export const config = {
    matcher: ['/((?!_next|favicon\\.ico).*)'],
};