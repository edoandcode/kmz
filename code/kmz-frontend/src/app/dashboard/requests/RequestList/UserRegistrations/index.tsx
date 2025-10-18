'use client'
import React from 'react';

import { useSession } from 'next-auth/react';
import useSWR from 'swr';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { UserRegistrationResponseDto } from '@/types/api/request/types';

import RequestGroupWrapper from '../../RequestGroupWrapper';
import UserRegistrationCard from './UserRegistrationCard';

const fetcher = (url: string, token?: string) =>
    get<UserRegistrationResponseDto[]>(url, {
        headers: { Authorization: `Bearer ${token}` },
    });

const UserRegistrations = ({ canProcess }: { canProcess: boolean }) => {


    const { data: session } = useSession();

    const endpoint = canProcess ? `/${API.REQUESTS_USER_REGISTRATION}` : `/${API.REQUESTS_USER_REGISTRATION_MY}`;

    const { data: userRegistrationRequests, error, isLoading } = useSWR(
        session ? [endpoint, session?.user?.accessToken] : null,
        ([url, token]) => fetcher(url, token),
        {
            revalidateOnFocus: true,
            revalidateOnReconnect: true,
            shouldRetryOnError: true,
            refreshInterval: 1000, // Refresh every 1 second
        }
    );


    if (isLoading) return <div>Loading...</div>;
    if (error) return <div>Error loading user registrations</div>;
    if (!userRegistrationRequests || userRegistrationRequests.length === 0) return <div>No user registrations found</div>;


    return (
        <RequestGroupWrapper>
            {userRegistrationRequests?.map((request: UserRegistrationResponseDto) => {
                return (
                    <UserRegistrationCard
                        key={request.id}
                        request={request}
                        canProcess={canProcess}
                    />
                )
            })}
        </RequestGroupWrapper>
    )
}

export default UserRegistrations