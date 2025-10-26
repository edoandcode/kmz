'use client'
import React from 'react';

import { clsx } from 'clsx';
import { Session } from 'next-auth';
import useSWR from 'swr';

import { get } from '@/services/api';
import { API, API_REFRESH_INTERVAL } from '@/settings/api';
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
            refreshInterval: API_REFRESH_INTERVAL, // Refresh every 15 seconds
        }
    );


    if (isLoading) return <div>Loading...</div>;
    if (error) return <div>Error loading events</div>;
    if (!events || events.length === 0) return <div>No events found</div>;


    console.log('events', events);


    return (
        <div className={clsx(
            "flex gap-4 w-full flex-col flex-grow-0",
            "xl:flex-row xl:flex-wrap"
        )}
        >
            {
                events.map((event, index) => (
                    <ContentCard
                        key={event.name + index}
                        content={event}
                        session={session}
                    />
                ))
            }
        </div >
    )
}

export default Events