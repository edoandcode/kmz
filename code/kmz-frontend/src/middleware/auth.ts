import { getToken } from 'next-auth/jwt';
import { NextRequest, NextResponse } from 'next/server';

import { ROUTES } from '@/settings/routes';

async function middleware(request: NextRequest) {

    const token = await getToken({ req: request, secret: process.env.NEXTAUTH_SECRET });

    if (!token) {
        const url = new URL(`/${ROUTES.LOGIN}`, request.url);
        return NextResponse.redirect(url)
    }

    return NextResponse.next()


}


export default middleware;