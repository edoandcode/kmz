import React from 'react';

import { Session } from 'next-auth';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { ContentPublicationResponseDto } from '@/types/api/request/types';

import RequestGroupWrapper from '../../RequestGroupWrapper';
import ContentPublicationCard from './ContentPublicationCard';

const ContentPublications = async ({ session, canProcess }: { session: Session | null, canProcess: boolean }) => {


    let publicationRequests: ContentPublicationResponseDto[] = [];

    try {
        const endpoint = canProcess ? `/${API.REQUESTS_CONTENTS_PUBLICATION}` : `/${API.REQUEST_CONTENTS_PUBLICATION_MY}`;
        publicationRequests = await get<ContentPublicationResponseDto[]>(endpoint, {
            headers: { Authorization: `Bearer ${session?.user?.accessToken}` },
        });
    } catch (error) {
        console.error("Error fetching content publication requests", error);
    }


    return (
        <RequestGroupWrapper>
            {publicationRequests?.map((request: ContentPublicationResponseDto) => {
                return (
                    <ContentPublicationCard
                        key={request.id}
                        request={request}
                        canProcess={canProcess}
                    />
                )
            })}
        </RequestGroupWrapper>
    )
}

export default ContentPublications;