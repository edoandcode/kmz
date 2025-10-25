'use client'
import React from 'react';

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

const Processes = ({ session }: { session: Session | null }) => {

    const { data: processes, error, isLoading } = useSWR(
        session ? [`/${API.MY_PROCESSES}`, session?.user?.accessToken] : null,
        ([url, token]) => fetcher(url, token),
        {
            revalidateOnFocus: true,
            revalidateOnReconnect: true,
            shouldRetryOnError: true,
            refreshInterval: API_REFRESH_INTERVAL, // Refresh every 1 second
        }
    );


    if (isLoading) return <div>Loading...</div>;
    if (error) return <div>Error loading processes</div>;
    if (!processes || processes.length === 0) return <div>No processes found</div>;


    console.log('processes', processes);


    return (
        <div className="flex gap-4 flex-wrap align-stretch justify-items-stretch">
            {processes.map((process, index) => (
                <ContentCard
                    key={process.name + index}
                    content={process}
                    session={session}
                />
            ))}
        </div>
    )
}

export default Processes