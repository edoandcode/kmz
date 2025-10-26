'use client'
import React from 'react';

import { Session } from 'next-auth';
import { useSession } from 'next-auth/react';

import { UserRole } from '@/types/api/user/types';

import EventList from './Events';
import ProcessedProducts from './ProcessedProducts';
import ProcessList from './Processes';
import Products from './Products';

const roleContentMap: Partial<Record<UserRole, { component: React.ElementType, label: string }[]>> = {
    [UserRole.PRODUCER]: [{ component: Products, label: 'I miei prodotti' }],
    [UserRole.PROCESSOR]: [{ component: ProcessList, label: 'I miei processi' }, { component: ProcessedProducts, label: 'Prodotti lavorati' }],
    [UserRole.FACILITATOR]: [{ component: EventList, label: 'Eventi organizzati' }],
};

const ContentsList = () => {

    const { data: session } = useSession()

    const userRoles = session?.user?.roles as UserRole[];


    return (
        <>
            {userRoles?.filter((role) => !!roleContentMap[role]).map(role => {
                return (
                    <div key={role}>
                        {roleContentMap[role]?.map(content => {
                            const ContentComponent = content.component;
                            const label = content.label;
                            return (
                                <div key={label} className="mb-8 w-full">
                                    <h2 className="text-2xl font-semibold mb-4">{label}</h2>
                                    <ContentComponent session={session} />
                                </div>
                            )
                        })}
                    </div>
                )
            })}
        </>
    )
}

export default ContentsList