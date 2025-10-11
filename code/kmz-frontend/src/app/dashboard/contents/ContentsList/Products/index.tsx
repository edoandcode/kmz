'use client'
import React from 'react';

import { Session } from 'next-auth';
import useSWR from 'swr';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { ProductSchema } from '@/validation/contents/product/schema';

import ProductCard from './ProductCard';

const fetcher = (url: string, token?: string) =>
    get<ProductSchema[]>(url, {
        headers: { Authorization: `Bearer ${token}` },
    });

const ProductList = ({ session }: { session: Session | null }) => {

    const { data: products, error, isLoading } = useSWR(
        session ? [`/${API.MY_PRODUCTS}`, session?.user?.accessToken] : null,
        ([url, token]) => fetcher(url, token),
        {
            revalidateOnFocus: true,
            refreshInterval: 10000, // Refresh every 10 seconds
        }
    );


    if (isLoading) return <div>Loading...</div>;
    if (error) return <div>Error loading products</div>;
    if (!products || products.length === 0) return <div>No products found</div>;


    console.log('products', products);


    return (
        <div className="flex gap-4 flex-wrap align-stretch justify-items-stretch">
            {products.map((product, index) => (
                <ProductCard key={product.name + index} product={product} />
            ))}
        </div>
    )
}

export default ProductList