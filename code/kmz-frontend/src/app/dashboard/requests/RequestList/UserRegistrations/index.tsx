import React from 'react';

import { Session } from 'next-auth';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { UserRegistrationResponseDto } from '@/types/api/request/types';

import RequestGroupWrapper from '../../RequestGroupWrapper';
import UserRegistrationCard from './UserRegistrationCard';

const UserRegistrations = async ({ session }: { session: Session | null }) => {

    let publicationRequests: UserRegistrationResponseDto[] = [];

    try {

        publicationRequests = await get<UserRegistrationResponseDto[]>(`/${API.REQUEST_MY_USER_REGISTRATION}`, {
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
                    <UserRegistrationCard key={request.id} request={request} />
                )
            })}
        </RequestGroupWrapper>
    )
}

export default UserRegistrations