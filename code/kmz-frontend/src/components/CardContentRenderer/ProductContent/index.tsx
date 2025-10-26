import React from 'react';

import KeyValueItem from '@/components/KeyValueItem';
import OpenLayersMap from '@/components/OpenLayerMap';
import { CardDescription, CardTitle } from '@/components/ui/card';

import { Content, ProductContent as ProductContentType } from '@/types/api/content/types';

const ProductContent = ({ content }: { content: Content }) => {

    const product = content as ProductContentType;

    return (

        <div className="flex h-full">
            <div className="w-[calc(50%-10px)]">
                <div className="flex flex-col gap-2 mb-3">
                    <CardTitle>{product.name}</CardTitle>
                    <CardDescription>{product.description}</CardDescription>
                </div>
                <p className='font-medium '>{'Metodo di Coltivazione'}</p>
                <CardDescription>{product.cultivationMethod}</CardDescription>
                <div className="flex flex-col gap-2 mt-4 text-sm">
                    <KeyValueItem label={'Data di semina'} value={new Date(product.sowingDate).toLocaleDateString()} />
                    <KeyValueItem label={'Data di raccolta'} value={new Date(product.harvestDate).toLocaleDateString()} />
                </div>
            </div>
            {product.cultivationPlace ? (
                <div className="w-[calc(50%-10px)] max-h-full">
                    <div
                        className="w-full h-full bg-neutral-600"
                    >
                        <OpenLayersMap
                            lat={product.cultivationPlace?.location?.latitude}
                            lon={product.cultivationPlace?.location?.longitude}
                            zoom={10}
                        ></OpenLayersMap>
                    </div>
                </div>
            ) : null}
        </div>
    )
}

export default ProductContent