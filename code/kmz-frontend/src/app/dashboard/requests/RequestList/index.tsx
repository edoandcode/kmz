import React from 'react';

import { APP_LABELS } from '@/settings/app-labels';
import { UserRole } from '@/types/api/user/types';

import ContentPublications from './ContentPublications';
import UserRegistrations from './UserRegistrations';

import type { Session } from 'next-auth';

const RequestList = ({ session }: { session: Session | null }) => {

    const userRoles = session?.user?.roles

    if (!userRoles) return null

    const isAdmin = userRoles.includes(UserRole.ADMINISTRATOR);

    return (
        <div className="flex flex-col gap-4">
            <div>
                <h2 className="text-2xl font-medium mb-4">{APP_LABELS.CONTENTS_PUBLICATION_REQUESTS_SENT}</h2>
                <ContentPublications session={session}></ContentPublications>
            </div>
            <div>
                <h2 className="text-2xl font-medium mb-4">{APP_LABELS.USER_REGISTRATION}</h2>
                <UserRegistrations session={session}></UserRegistrations>
            </div>

        </div>
    )
}

export default RequestList