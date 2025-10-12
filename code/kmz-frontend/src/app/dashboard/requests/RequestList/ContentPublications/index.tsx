import React from 'react';

import { Session } from 'next-auth';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { ContentPublicationResponseDto } from '@/types/api/request/types';

import ContentPublicationCard from './ContentPublicationCard';

const ContentPublications = async ({ session }: { session: Session | null }) => {

    const publicationRequests = await get<ContentPublicationResponseDto[]>(`/${API.REQUEST_MY_REQUESTS}`, {
        headers: { Authorization: `Bearer ${session?.user?.accessToken}` },
    });

    console.log('publicationRequests', publicationRequests);


    return (
        <div className='flex gap-4 flex-wrap align-stretch justify-items-stretch'>
            {publicationRequests?.map((request: ContentPublicationResponseDto) => {
                return (
                    <ContentPublicationCard key={request.id} request={request} />
                )
            })}
        </div>
    )
}

export default ContentPublications;