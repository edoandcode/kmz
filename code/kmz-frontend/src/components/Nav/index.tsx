'use client'
import React from 'react';

import { clsx } from 'clsx';
import { usePathname } from 'next/navigation';

import { APP_LABELS } from '@/settings/app-labels';
import { ROUTES } from '@/settings/routes';

import NavDesktop from './NavDesktop';
import NavMobile from './NavMobile';

export type NavItem = {
    label: string;
    route: string;
}


const Nav = () => {
    const pathname = usePathname();

    const navItems: NavItem[] = [
        { label: APP_LABELS.HOME, route: ROUTES.HOME },
        { label: APP_LABELS.DASHBOARD, route: ROUTES.DASHBOARD },
    ];

    return (
        <nav className={clsx(
            'flex gap-5 items-center '
        )}>
            <NavDesktop items={navItems} activePath={pathname} />
            <NavMobile items={navItems} activePath={pathname} />
        </nav >
    )
}

export default Nav