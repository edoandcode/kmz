'use client'
import React from 'react';

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

    const { data: products, error, isLoading } = useSWR(
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
    if (error) return <div>Error loading products</div>;
    if (!products || products.length === 0) return <div>No products found</div>;


    console.log('products', products);


    return (
        <div className="flex gap-4 flex-wrap align-stretch justify-items-stretch">
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