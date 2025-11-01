import { NextResponse } from 'next/server';

import { auth } from '@/services/next-auth';
import { ROUTES, SITE_URL } from '@/settings/routes';

export default async function authProxy() {
    const session = await auth();
    console.log('Auth middleware session:', session);
    if (!session) {
        return NextResponse.redirect(`${SITE_URL}/${ROUTES.LOGIN}`);
    }
    return NextResponse.next();
};