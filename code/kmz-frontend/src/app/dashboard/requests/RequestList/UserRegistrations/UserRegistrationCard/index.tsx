'use client'
import React from 'react';

import { Session } from 'next-auth';

import KeyValueItem from '@/components/KeyValueItem';

import { post } from '@/services/api';
import { API } from '@/settings/api';
import { UserRegistrationResponseDto } from '@/types/api/request/types';

import RequestCardWrapper from '../../../RequestCardWrapper';

const UserRegistrationCard = ({ request, canProcess, session }: { request: UserRegistrationResponseDto, canProcess: boolean, session: Session | null }) => {



    const onApproveRequest = async () => {
        // Implement approve request logic here
        try {
            await post<unknown>(`${API.REQUESTS_USER_APPROVE}/${request.id}`, null, {
                headers: {
                    Authorization: `Bearer ${session?.user?.accessToken}`,
                },
            });
        } catch (error) {
            console.error(`Error approving request with id: ${request.id}`, error);
        }
    }

    const onRejectRequest = async () => {
        // Implement reject request logic here
        try {
            await post(`${API.REQUESTS_USER_REJECT}/${request.id}`, null, {
                headers: {
                    Authorization: `Bearer ${session?.user?.accessToken}`,
                },
            });
        } catch (error) {
            console.error(`Error rejecting request with id: ${request.id}`, error);
        }
    }


    return (
        <RequestCardWrapper
            request={request}
            canProcess={canProcess}
            onApproveRequest={onApproveRequest}
            onRejectRequest={onRejectRequest}
        >
            <KeyValueItem
                label="nome"
                value={`${request.userFirstName} ${request.userLastName}`}
            />
            <KeyValueItem
                label="createdAt"
                value={new Date(request.createdAt).toLocaleDateString()}
            />
            <KeyValueItem
                label="ruolo richiesto"
                value={request.requestedRole}
            />
        </RequestCardWrapper>

    )
}

export default UserRegistrationCard