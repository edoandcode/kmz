'use client'

import React from 'react';

import clsx from 'clsx';
import { useSession } from 'next-auth/react';

import { Button } from '@/components/ui/button';
import { DialogTrigger } from '@/components/ui/dialog';

import { ContentType } from '@/types/api/content/types';
import { UserRole } from '@/types/api/user/types';

const roleContentMap: Partial<Record<UserRole, { contentType: ContentType, label: string }[]>> = {
    [UserRole.PRODUCER]: [{ contentType: ContentType.PRODUCT, label: 'Create new product' }],
    [UserRole.PROCESSOR]: [{ contentType: ContentType.PROCESS, label: 'Create new process' }, { contentType: ContentType.PROCESSED_PRODUCT, label: 'Create new processed product' }],
    [UserRole.FACILITATOR]: [{ contentType: ContentType.EVENT, label: 'Create new event' }],
};


const CreateContentButtons = ({ setContentType }: { setContentType: (type: ContentType) => void }) => {

    const { data: session } = useSession();

    const userRoles = session?.user?.roles as UserRole[]


    return (
        <div className={clsx(
            "flex gap-3 justify-end"
        )}>
            {userRoles?.filter((role) => !!roleContentMap[role]).map(role => {
                const contents = roleContentMap[role];

                return (
                    <>
                        {contents?.map(content => {
                            return (
                                <DialogTrigger asChild key={content.label}>
                                    <Button
                                        variant="outline_primary"
                                        onClick={() => setContentType(content.contentType)}
                                    >
                                        {content.label}
                                    </Button>
                                </DialogTrigger>
                            )
                        })}
                    </>
                )
            }
            )}
        </div>
    )
}

export default CreateContentButtons