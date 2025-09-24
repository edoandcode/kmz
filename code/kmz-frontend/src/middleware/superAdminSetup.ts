import { NextRequest, NextResponse } from "next/server";
import { getSystemStatus } from "@/services/api";
import { ROUTES } from "@/settings/routes";

export default async function middleware(request: NextRequest) {

    const systemStatus = await getSystemStatus();

    if (!systemStatus.superAdminExists && request.nextUrl.pathname !== `/${ROUTES.SIGNUP}`)
        return NextResponse.redirect(new URL(ROUTES.SIGNUP, request.url));

    return NextResponse.next();

}