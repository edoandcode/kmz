import React from 'react';

import {
    Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle
} from '@/components/ui/card';

import type { ProductSchema } from '@/validation/contents/product/schema';

const ProductCard = ({ product }: { product: ProductSchema }) => {
    return (
        <Card className='basis-[calc(50%-1rem)] h-full'>
            <CardHeader>
                <CardTitle>{product.name}</CardTitle>
            </CardHeader>
            <CardContent>
                <CardDescription>{product.description}</CardDescription>
            </CardContent>
            <CardContent className='text-sm'>
                <p className='font-medium '>{'Metodo di Coltivazione:'}</p>
                <CardDescription>{product.cultivationMethod}</CardDescription>
                <div className="flex flex-col gap-2 mt-4 text-sm">
                    <p className='font-medium'>{'Data di semina: '}<span className='font-normal'>{new Date(product.sowingDate).toLocaleDateString()}</span></p>
                    <p className='font-medium'>{'Data di raccolta: '}<span className='font-normal'>{new Date(product.harvestDate).toLocaleDateString()}</span></p>
                </div>
            </CardContent>
            <CardFooter>
                <div>Footer</div>
            </CardFooter>
        </Card>
    )
}

export default ProductCard