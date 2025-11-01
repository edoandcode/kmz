'use client'
import React from 'react';

import clsx from 'clsx';
import { Session } from 'next-auth';
import useSWR from 'swr';

import { get } from '@/services/api';
import { API, API_REFRESH_INTERVAL } from '@/settings/api';
import { ProductContent } from '@/types/api/content/types';

import ContentCard from '../ContentCard';

const fetcher = (url: string, token?: string) =>
    get<ProductContent[]>(url, {
        headers: { Authorization: `Bearer ${token}` },
    });

const Products = ({ session }: { session: Session | null }) => {

    const { data: products, isLoading } = useSWR(
        session ? [`/${API.MY_PRODUCTS}`, session?.user?.accessToken] : null,
        ([url, token]) => fetcher(url, token),
        {
            revalidateOnFocus: true,
            revalidateOnReconnect: true,
            shouldRetryOnError: true,
            refreshInterval: API_REFRESH_INTERVAL, // Refresh every 1 second
        }
    );


    if (isLoading) return <div>Loading...</div>;
    if (!products || products.length === 0) return <div>No products found</div>;


    console.log('products', products);


    return (
        <div className={clsx(
            "flex gap-4 w-full flex-col flex-grow-0",
            "xl:flex-row xl:flex-wrap"
        )}>
            {products.map((product, index) => (
                <ContentCard
                    key={product.name + index}
                    content={product}
                    session={session}
                />
            ))}
        </div>
    )
}

export default Products