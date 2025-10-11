import React from 'react';

import { Session } from 'next-auth';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { ProductSchema } from '@/validation/contents/product/schema';

import ProductCard from './ProductCard';

const ProductList = async ({ session }: { session: Session | null }) => {

    const products = await get<ProductSchema[]>(`/${API.MY_PRODUCTS}`, {
        headers: {
            Authorization: `Bearer ${session?.user?.accessToken}`
        }
    });


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