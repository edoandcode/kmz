'use client'
import React from 'react';

import { clsx } from 'clsx';
import { signOut, useSession } from 'next-auth/react';
import Link from 'next/link';

import { Button } from '@/components/ui/button';

import { APP_LABELS } from '@/settings/app-labels';
import { ROUTES } from '@/settings/routes';

const MemberArea = () => {
    const { status, data: session } = useSession();

    console.log('Session status:', status);
    console.log('Session data:', session);

    return (
        <div className={clsx(
            'flex gap-5 items-center',
            ' border-neutral-300 border-l-solid border-l-1 pl-5'

        )}>
            {session?.user.firstName ? (
                <span className="font-medium text-green-500">{session.user.firstName}</span>
            ) : null}
            {Object.keys(session?.user || {}).length ? (
                <>
                    <Button
                        variant="outline"
                        size="sm"
                        onClick={() => {
                            console.log('Logging out')
                            signOut();
                        }}
                    >{APP_LABELS.LOGOUT}</Button>
                </>
            ) : (
                <Button
                    variant="outline"
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