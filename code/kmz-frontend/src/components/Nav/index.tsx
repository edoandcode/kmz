'use client'
import React from 'react';

import { clsx } from 'clsx';
import Link from 'next/link';
import { usePathname } from 'next/navigation';

import { APP_LABELS } from '@/settings/app-labels';
import { ROUTES } from '@/settings/routes';

import MemberArea from './MemberArea';

const Nav = () => {
    const pathname = usePathname();

    const naveItems = [
        { label: APP_LABELS.HOME, route: ROUTES.HOME },
        { label: APP_LABELS.DASHBOARD, route: ROUTES.DASHBOARD },
    ];

    return (
        <nav className={clsx(
            'flex gap-5 items-center '
        )}>
            <ul className={clsx(
                'flex gap-7 list-none m-0 p-0'
            )}>
                {naveItems.map((item) => (
                    <li key={item.route} className={clsx({ 'font-medium': pathname === item.route || pathname === `/${item.route}` })}>
                        <Link href={`/${item.route}`}>{item.label}</Link>
                    </li>
                ))}
            </ul>
            <MemberArea />
        </nav >
    )
}

export default Nav