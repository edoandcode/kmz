import { NextRequest, NextResponse } from 'next/server';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { ROUTES } from '@/settings/routes';

export default async function middleware(request: NextRequest) {

    const systemStatus = await get<{ superAdminExists: boolean }>(`${API.SYSTEM_STATUS}`)

    if (!systemStatus.superAdminExists && request.nextUrl.pathname !== `/${ROUTES.SIGNUP}`)
        return NextResponse.redirect(new URL(ROUTES.SIGNUP, request.url));

    return NextResponse.next();

}