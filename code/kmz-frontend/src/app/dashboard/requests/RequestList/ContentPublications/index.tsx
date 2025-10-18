import React from 'react';

import { Session } from 'next-auth';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { ContentPublicationResponseDto } from '@/types/api/request/types';

import RequestGroupWrapper from '../../RequestGroupWrapper';
import ContentPublicationCard from './ContentPublicationCard';

const ContentPublications = async ({ session }: { session: Session | null }) => {


    let publicationRequests: ContentPublicationResponseDto[] = [];

    try {

        publicationRequests = await get<ContentPublicationResponseDto[]>(`/${API.REQUEST_CONTENTS_MY_REQUESTS}`, {
            headers: { Authorization: `Bearer ${session?.user?.accessToken}` },
        });
    } catch (error) {
        console.error("Error fetching content publication requests", error);
    }


    return (
        <RequestGroupWrapper>
            {publicationRequests?.map((request: ContentPublicationResponseDto) => {
                return (
                    <ContentPublicationCard key={request.id} request={request} />
                )
            })}
        </RequestGroupWrapper>
    )
}

export default ContentPublications;