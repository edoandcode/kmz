import React from 'react';

import { Session } from 'next-auth';

import { UserRole } from '@/types/api/user/types';

import EventList from './Events';
import ProcessList from './Processes';
import Products from './Products';

const roleContentMap: Partial<Record<UserRole, { component: React.ElementType, label: string }>> = {
    [UserRole.PRODUCER]: { component: Products, label: 'I miei prodotti' },
    [UserRole.PROCESSOR]: { component: ProcessList, label: 'I miei processi' },
    [UserRole.FACILITATOR]: { component: EventList, label: 'Eventi organizzati' },
};

const ContentsList = ({ session }: { session: Session | null }) => {

    const userRoles = session?.user?.roles as UserRole[];


    return (
        <div>
            {userRoles?.filter((role) => !!roleContentMap[role]).map(role => {
                const ContentComponent = roleContentMap[role]!.component;
                const label = roleContentMap[role]!.label;
                return (
                    <div key={role} className="mb-8">
                        <h2 className="text-2xl font-semibold mb-4">{label}</h2>
                        <ContentComponent session={session} />
                    </div>
                )
            })}
        </div>
    )
}

export default ContentsList