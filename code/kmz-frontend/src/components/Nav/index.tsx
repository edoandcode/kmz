'use client'
import React from 'react';

import { signOut, useSession } from 'next-auth/react';
import Link from 'next/link';

import { Button } from '@/components/ui/button';

import { ROUTES } from '@/settings/routes';

const Nav = () => {

    const { data: session } = useSession();


    console.log('session', session)

    return (
        <div>
            {session?.user.firstName}
            { }
            {session ? (
                <Button
                    variant="outline"
                    onClick={() => {
                        console.log('Logging out')
                        signOut();
                    }}
                >LOGOUT</Button>
            ) : (
                <Button
                    variant="outline"
                    asChild
                >
                    <Link href={`/${ROUTES.LOGIN}`}>
                        LOGIN
                    </Link>
                </Button>
            )}

        </div>
    )
}

export default Nav