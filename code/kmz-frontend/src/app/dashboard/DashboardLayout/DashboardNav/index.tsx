'use client'
import React from 'react';

import clsx from 'clsx';
import { CircleQuestionMark, SquareChartGantt } from 'lucide-react';
import { useSession } from 'next-auth/react';
import Link from 'next/link';
import { usePathname } from 'next/navigation';

import { APP_LABELS } from '@/settings/app-labels';
import { ROUTES } from '@/settings/routes';
import { UserRole } from '@/types/api/user/types';

type MenuItem = {
    label: string
    path: string
    roles: UserRole[]
    icon?: React.ReactNode
}


const MENU_ITEMS: MenuItem[] = [
    {
        label: APP_LABELS.MY_REQUESTS,
        path: ROUTES.DASHBOARD_REQUESTS,
        roles: [UserRole.ADMINISTRATOR, UserRole.FACILITATOR, UserRole.PROCESSOR, UserRole.PRODUCER, UserRole.CURATOR],
        icon: <CircleQuestionMark></CircleQuestionMark>
    },
    {
        label: APP_LABELS.MY_CONTENTS,
        path: ROUTES.DASHBOARD_CONTENTS,
        roles: [UserRole.PRODUCER, UserRole.PROCESSOR, UserRole.FACILITATOR],
        icon: <SquareChartGantt></SquareChartGantt>
    },
];


const DashboardNav = () => {

    const { data: session } = useSession();

    const pathname = usePathname();

    const userRoles = session?.user?.roles

    if (!userRoles) return null

    const items = MENU_ITEMS.filter(item => item.roles.some(role => userRoles.includes(role)))

    return (
        <div>
            <ul className={clsx(
                "flex flex-col gap-3"
            )}>
                {items.map(item => (
                    <li key={item.path}>
                        <Link
                            href={`/${item.path}`} className={clsx(
                                "flex gap-4 items-center",
                                "font-medium text-lg",
                                { 'text-primary': pathname === `/${item.path}` }
                            )}>
                            {item.icon}
                            <span>{item.label}</span>
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    )
}

export default DashboardNav