'use client'
import React from 'react';

import { Session } from 'next-auth';
import useSWR from 'swr';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { ProcessContent } from '@/types/api/content/types';

import ContentCard from '../ContentCard';

const fetcher = (url: string, token?: string) =>
    get<ProcessContent[]>(url, {
        headers: { Authorization: `Bearer ${token}` },
    });

const Events = ({ session }: { session: Session | null }) => {

    const { data: events, error, isLoading } = useSWR(
        session ? [`/${API.MY_EVENTS}`, session?.user?.accessToken] : null,
        ([url, token]) => fetcher(url, token),
        {
            revalidateOnFocus: true,
            revalidateOnReconnect: true,
            shouldRetryOnError: true,
            refreshInterval: 1000, // Refresh every 1 second
        }
    );


    if (isLoading) return <div>Loading...</div>;
    if (error) return <div>Error loading events</div>;
    if (!events || events.length === 0) return <div>No events found</div>;


    console.log('events', events);


    return (
        <div className="flex gap-4 flex-wrap align-stretch justify-items-stretch">
            {events.map((event, index) => (
                <ContentCard
                    key={event.name + index}
                    content={event}
                    session={session}
                />
            ))}
        </div>
    )
}

export default Events