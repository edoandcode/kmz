'use client'

import React, { useMemo } from 'react';

import clsx from 'clsx';
import { useSession } from 'next-auth/react';

import { Button } from '@/components/ui/button';
import { DialogTrigger } from '@/components/ui/dialog';

import { ContentType, UserRole } from '@/types/api/data-types';

const CreateContentButtons = ({ setContentType }: { setContentType: (type: ContentType) => void }) => {

    const { data: session } = useSession();

    const userRoles = session?.user?.roles as UserRole[]


    const buttons = useMemo(() => {
        return userRoles.map(role => {
            switch (role) {
                case UserRole.PRODUCER:
                    return (
                        <DialogTrigger asChild key={role}>
                            <Button
                                variant="secondary"
                                onClick={() => setContentType(ContentType.PRODUCT)}
                            >
                                Create new product
                            </Button>
                        </DialogTrigger>
                    );
                case UserRole.PROCESSOR:
                    return (
                        <DialogTrigger asChild key={role}>
                            <Button
                                variant="secondary"
                                onClick={() => setContentType(ContentType.PROCESS)}
                            >
                                Create new process
                            </Button>
                        </DialogTrigger>
                    )
                case UserRole.FACILITATOR:
                    return (
                        <DialogTrigger asChild key={role}>
                            <Button
                                variant="secondary"
                                onClick={() => setContentType(ContentType.EVENT)}
                            >
                                Create new event
                            </Button>
                        </DialogTrigger>
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