'use client'
import React from 'react';

import { clsx } from 'clsx';
import { CircleUserRound } from 'lucide-react';
import { signOut, useSession } from 'next-auth/react';
import Link from 'next/link';

import { Button } from '@/components/ui/button';

import { APP_LABELS } from '@/settings/app-labels';
import { ROUTES } from '@/settings/routes';

const MemberArea = () => {
    const { status, data: session } = useSession();

    console.log('Session status:', status);
    console.log('Session data:', session);

    if (!session)
        return null;



    return (
        <div className={clsx(
            'flex gap-5 items-center justify-between',
            ' border-neutral-300 ',
            'border-t-solid border-t-1',
            'pt-5',
            'md:border-t-0',
            'md:border-l-solid md:border-l-1 ',
            'md:pl-5 md:pt-0'
        )}>
            {session?.user?.firstName ? (
                <Link
                    href={`/${ROUTES.DASHBOARD}`}
                    className={clsx(
                        "flex gap-3 items-center",
                        "font-medium text-primary"
                    )}>
                    <CircleUserRound></CircleUserRound>{session.user.firstName}
                </Link>
            ) : null}
            {Object.keys(session?.user || {}).length ? (
                <>
                    <Button
                        variant="primary"
                        size="sm"
                        onClick={() => {
                            console.log('Logging out')
                            signOut();
                        }}
                    >{APP_LABELS.LOGOUT}</Button>
                </>
            ) : (
                <Button
                    variant="default"
                    asChild
                >
                    <Link href={`/${ROUTES.LOGIN}`}>
                        {APP_LABELS.LOGIN}
                    </Link>
                </Button>
            )}
        </div>
    )
}

export default MemberArea