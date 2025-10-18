import React from 'react';

import { Session } from 'next-auth';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { UserRegistrationResponseDto } from '@/types/api/request/types';

import RequestGroupWrapper from '../../RequestGroupWrapper';
import UserRegistrationCard from './UserRegistrationCard';

const UserRegistrations = async ({ session, canProcess }: { session: Session | null, canProcess: boolean }) => {

    let publicationRequests: UserRegistrationResponseDto[] = [];

    try {
        const endpoint = canProcess ? `/${API.REQUESTS_USER_REGISTRATION}` : `/${API.REQUESTS_USER_REGISTRATION_MY}`;
        publicationRequests = await get<UserRegistrationResponseDto[]>(endpoint, {
            headers: { Authorization: `Bearer ${session?.user?.accessToken}` },
        });
    } catch (error) {
        console.error("Error fetching user registration requests", error);
    }

    console.log('publicationRequests', publicationRequests);

    return (
        <RequestGroupWrapper>
            {publicationRequests?.map((request: UserRegistrationResponseDto) => {
                return (
                    <UserRegistrationCard
                        key={request.id}
                        request={request}
                        canProcess={canProcess}
                        session={session}
                    />
                )
            })}
        </RequestGroupWrapper>
    )
}

export default UserRegistrations