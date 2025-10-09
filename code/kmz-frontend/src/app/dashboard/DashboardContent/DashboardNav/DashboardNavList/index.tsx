'use client'
import React from 'react';

import clsx from 'clsx';
import { CircleQuestionMark, SquareChartGantt } from 'lucide-react';
import { useSession } from 'next-auth/react';
import Link from 'next/link';

import { UserRole } from '@/types/api/data-types';

type MenuItem = {
    label: string
    path: string
    roles: UserRole[]
    icon?: React.ReactNode
}



const MENU_ITEMS: MenuItem[] = [
    {
        label: "Le mie richieste",
        path: "/dashboard/requests",
        roles: [UserRole.ADMINISTRATOR, UserRole.FACILITATOR, UserRole.PROCESSOR, UserRole.PRODUCER, UserRole.CURATOR],
        icon: <CircleQuestionMark></CircleQuestionMark>
    },
    {
        label: "I Miei Contenuti",
        path: "/dashboard/content",
        roles: [UserRole.PRODUCER, UserRole.PROCESSOR, UserRole.FACILITATOR],
        icon: <SquareChartGantt></SquareChartGantt>
    },
];


const DashboardNavList = () => {

    const { data: session } = useSession();

    const userRole = session?.user?.roles?.filter(r => r !== UserRole.GENERIC_USER)[0] as UserRole

    if (!userRole) return null

    const items = MENU_ITEMS.filter(item => item.roles.includes(userRole))

    return (
        <div>
            <ul>
                {items.map(item => (
                    <li key={item.path}>
                        <Link href={item.path} className={clsx(
                            "flex gap-4 items-center",
                            "font-medium text-lg"
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

export default DashboardNavList