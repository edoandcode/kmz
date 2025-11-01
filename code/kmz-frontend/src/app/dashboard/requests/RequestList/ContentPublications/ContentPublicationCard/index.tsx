'use client'
import React from 'react';

import { useSession } from 'next-auth/react';

import CardContentRenderer from '@/components/CardContentRenderer';

import { post } from '@/services/api';
import { API } from '@/settings/api';
import { ContentPublicationResponseDto } from '@/types/api/request/types';

import RequestCardWrapper from '../../../RequestCardWrapper';

const ContentPublicationCard = ({ request, canProcess }: { request: ContentPublicationResponseDto, canProcess: boolean }) => {

    const { data: session } = useSession();

    const onApproveRequest = async () => {
        console.log('token', `Bearer ${session?.user?.accessToken}`);
        try {
            await post<unknown>(`${API.REQUEST_CONTENTS_APPROVE_CONTENT}/${request.id}`, null, {
                headers: {
                    Authorization: `Bearer ${session?.user?.accessToken}`,
                },
            });
        } catch (error) {
            console.error('Error approving request:', error);
        }
    }

    const onRejectRequest = async () => {
        try {
            await post<unknown>(`${API.REQUEST_CONTENTS_REJECT_CONTENT}/${request.id}`, null, {
                headers: {
                    Authorization: `Bearer ${session?.user?.accessToken}`,
                },
            });
        } catch (error) {
            console.error('Error rejecting request:', error);
        }
    }


    return (
        <RequestCardWrapper
            request={request}
            canProcess={canProcess}
            onApproveRequest={onApproveRequest}
            onRejectRequest={onRejectRequest}
        >
            <CardContentRenderer content={request.content} />
        </RequestCardWrapper>
    )
}

export default ContentPublicationCard