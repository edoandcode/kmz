import React, { useEffect, useRef } from 'react';

import clsx from 'clsx';
import { Menu } from 'lucide-react';
import Link from 'next/link';

import { isActiveRoute, NavItem } from '@/components/Nav';
import { Sheet, SheetContent, SheetTrigger } from '@/components/ui/sheet';

import MemberArea from '../MemberArea';

const NavMobile = ({ items, activePath }: { items: NavItem[], activePath: string }) => {
    const $trigger = useRef<HTMLButtonElement>(null);

    useEffect(() => {
        if ($trigger.current && $trigger.current.dataset.state === 'open')
            $trigger.current.click()
    }, [activePath])


    return (
        <div className={clsx(
            'md:hidden',
            'text-primary',

        )}>
            <Sheet>
                <SheetTrigger ref={$trigger} >
                    <Menu className={clsx(
                        'cursor-pointer'
                    )} />
                </SheetTrigger>
                <SheetContent
                    className={clsx(
                        'bg-secondary z-10001',
                        'pt-[var(--header-height)]',
                        'px-5'
                    )}
                >

                    <ul className={clsx(
                        'flex flex-col gap-5 list-none m-0 p-0 mb-5'
                    )}>
                        {items.map((item) => (
                            <li key={item.route} className={clsx({ 'font-medium': isActiveRoute(activePath, item.route) })}>
                                <Link href={`/${item.route}`}>{item.label}</Link>
                            </li>
                        ))}
                    </ul>
                    <MemberArea></MemberArea>
                </SheetContent>
            </Sheet>
        </div>
    )
}

export default NavMobile