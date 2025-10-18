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
    const isCurator = userRoles.includes(UserRole.CURATOR);

    const contentPublicationRequestsLabel = isAdmin || isCurator ? APP_LABELS.CONTENTS_PUBLICATION_REQUESTS : APP_LABELS.CONTENTS_PUBLICATION_REQUESTS_SENT;
    const userRegistrationRequestsLabel = isAdmin ? APP_LABELS.USER_REGISTRATION_REQUESTS : APP_LABELS.USER_REGISTRATION_REQUESTS_SENT;

    return (
        <div className="flex flex-col gap-4">
            <div>
                <h2 className="text-2xl font-medium mb-4">{contentPublicationRequestsLabel}</h2>
                <ContentPublications
                    session={session}
                    canProcess={isAdmin || isCurator}
                ></ContentPublications>
            </div>
            <div>
                <h2 className="text-2xl font-medium mb-4">{userRegistrationRequestsLabel}</h2>
                <UserRegistrations
                    session={session}
                    canProcess={isAdmin}
                ></UserRegistrations>
            </div>

        </div>
    )
}

export default RequestList