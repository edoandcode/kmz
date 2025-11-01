'use client'

import { PropsWithChildren, useEffect, useState } from 'react';

import { usePathname, useRouter } from 'next/navigation';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { ROUTES } from '@/settings/routes';

const SetupAdminRedirect = ({ children }: PropsWithChildren) => {

    const pathname = usePathname();
    const router = useRouter()
    const [systemStatus, setSystemStatus] = useState<{ superAdminExists: boolean } | null>(null);

    useEffect(() => {
        const setupAdminRedirect = async () => {
            const systemStatus = await get<{ superAdminExists: boolean }>(`${API.SYSTEM_STATUS}`)
            setSystemStatus(systemStatus);
            if (!systemStatus.superAdminExists && !pathname.includes(`/${ROUTES.SIGNUP_SUPERADMIN}`))
                return router.push(`/${ROUTES.SIGNUP_SUPERADMIN}`);
        }

        setupAdminRedirect();
    }, [pathname, router])

    return (
        <>
            {systemStatus ? children : null}
        </>
    )
}

export default SetupAdminRedirect