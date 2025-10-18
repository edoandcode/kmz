'use client'
import React from 'react';

import { useSession } from 'next-auth/react';
import useSWR from 'swr';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { ContentPublicationResponseDto } from '@/types/api/request/types';

import RequestGroupWrapper from '../../RequestGroupWrapper';
import ContentPublicationCard from './ContentPublicationCard';

const fetcher = (url: string, token?: string) =>
    get<ContentPublicationResponseDto[]>(url, {
        headers: { Authorization: `Bearer ${token}` },
    });


const ContentPublications = ({ canProcess }: { canProcess: boolean }) => {

    const { data: session } = useSession();


    const endpoint = canProcess ? `/${API.REQUESTS_CONTENTS_PUBLICATION}` : `/${API.REQUEST_CONTENTS_PUBLICATION_MY}`;

    const { data: publicationRequests, error, isLoading } = useSWR(
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
    if (error) return <div>Error loading publications</div>;
    if (!publicationRequests || publicationRequests.length === 0) return <div>No publications found</div>;


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