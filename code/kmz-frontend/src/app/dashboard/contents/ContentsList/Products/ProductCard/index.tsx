import React, { useCallback } from 'react';

import { Session } from 'next-auth';
import { toast } from 'sonner';

import KeyValueItem from '@/components/KeyValueItem';
import { Button } from '@/components/ui/button';
import {
    Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle
} from '@/components/ui/card';

import { post } from '@/services/api';
import { API } from '@/settings/api';
import { ProductContent } from '@/types/api/content/types';
import { ContentPublicationResponseDto } from '@/types/api/request/types';

const ProductCard = ({ product, session }: { product: ProductContent, session: Session | null }) => {

    const handlePublicationRequest = useCallback(async () => {
        // Handle publication request

        console.log('sesssion', session);

        try {
            const response = await post<ContentPublicationResponseDto>(`/${API.REQUEST_PUBLISH_CONTENT}/${product.id}`, {}, {
                headers: {
                    Authorization: `Bearer ${session?.user?.accessToken}`
                }
            })
            console.log('Publication request response:', response);
            toast.success('Publication request created successfully');
        } catch (error) {
            console.error('Error creating publication request:', error);
            toast.error('Error creating publication request');
        }
    }, [session, product])

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
                    <KeyValueItem label={'Data di semina'} value={new Date(product.sowingDate).toLocaleDateString()} />
                    <KeyValueItem label={'Data di raccolta'} value={new Date(product.harvestDate).toLocaleDateString()} />
                </div>
            </CardContent>
            <CardFooter>
                <Button
                    variant='primary'
                    onClick={handlePublicationRequest}
                >
                    {'Richiesta di pubblicazione'}
                </Button>
            </CardFooter>
        </Card>
    )
}

export default ProductCard