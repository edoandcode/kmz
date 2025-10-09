'use client'

import React, { useMemo } from 'react';

import clsx from 'clsx';
import { useSession } from 'next-auth/react';

import { Button } from '@/components/ui/button';

import { UserRole } from '@/types/api/data-types';

const CreateContentButtons = () => {

    const { data: session } = useSession();

    const userRoles = session?.user?.roles as UserRole[]


    const buttons = useMemo(() => {
        return userRoles.map(role => {
            switch (role) {
                case UserRole.PRODUCER:
                    return (
                        <Button variant="default" >
                            CREATE NEW PRODUCT
                        </Button>
                    );
                case UserRole.PROCESSOR:
                    return (
                        <Button variant="default">
                            CREATE NEW PROCESS
                        </Button>
                    )
                case UserRole.FACILITATOR:
                    return (
                        <Button variant="default">
                            CREATE NEW EVENT
                        </Button>
                    )
                default:
                    return null
            }
        });
    }, [userRoles])



    return (
        <div className={clsx(
            "flex gap-3 justify-end"
        )}>
            {buttons}
        </div>
    )
}

export default CreateContentButtons