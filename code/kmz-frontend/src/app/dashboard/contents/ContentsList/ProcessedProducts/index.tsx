'use client'
import React from 'react';

import clsx from 'clsx';
import { Session } from 'next-auth';
import useSWR from 'swr';

import { get } from '@/services/api';
import { API, API_REFRESH_INTERVAL } from '@/settings/api';
import { ProcessedProductContent } from '@/types/api/content/types';

import ContentCard from '../ContentCard';

const fetcher = (url: string, token?: string) =>
    get<ProcessedProductContent[]>(url, {
        headers: { Authorization: `Bearer ${token}` },
    });

const ProcessedProducts = ({ session }: { session: Session | null }) => {

    const { data: processedProducts, error, isLoading } = useSWR(
        session ? [`/${API.MY_PROCESSED_PRODUCTS}`, session?.user?.accessToken] : null,
        ([url, token]) => fetcher(url, token),
        {
            revalidateOnFocus: true,
            revalidateOnReconnect: true,
            shouldRetryOnError: true,
            refreshInterval: API_REFRESH_INTERVAL,
        }
    );


    if (isLoading) return <div>Loading...</div>;
    if (error) return <div>Error loading processes</div>;
    if (!processedProducts || processedProducts.length === 0) return <div>No processes found</div>;


    console.log('processes', processedProducts);


    return (
        <div className={clsx(
            "flex gap-4 w-full flex-col flex-grow-0",
            "xl:flex-row xl:flex-wrap"
        )}>
            {processedProducts.map((process, index) => (
                <ContentCard
                    key={process.name + index}
                    content={process}
                    session={session}
                />
            ))}
        </div>
    )
}

export default ProcessedProducts 