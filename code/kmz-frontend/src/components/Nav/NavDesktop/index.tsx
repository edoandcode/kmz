import React from 'react';

import { clsx } from 'clsx';
import Link from 'next/link';

import { isActiveRoute } from '@/components/Nav';

import MemberArea from '../MemberArea';

import type { NavItem } from '..';

const NavDesktop = ({ items, activePath }: { items: NavItem[], activePath: string }) => {

    console.log('asctivePath in NavDesktop:', activePath);

    return (
        <div className={clsx(
            'md:flex gap-5 items-center hidden'
        )}>
            <ul className={clsx(
                'flex gap-7 list-none m-0 p-0'
            )}>
                {items.map((item) => (
                    <li key={item.route} className={clsx({ 'font-medium': isActiveRoute(activePath, item.route) })}>
                        <Link href={`/${item.route}`}>{item.label}</Link>
                    </li>
                ))}
            </ul>
            <MemberArea />
        </div>

    )
}

export default NavDesktop