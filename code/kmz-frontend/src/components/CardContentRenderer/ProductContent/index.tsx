import React from 'react';

import CardLayout from '@/components/CardContentRenderer/CardLayout';
import KeyValueItem from '@/components/KeyValueItem';
import { CardDescription, CardTitle } from '@/components/ui/card';

import { Content, ProductContent as ProductContentType } from '@/types/api/content/types';

const ProductContent = ({ content }: { content: Content }) => {

    const product = content as ProductContentType;

    let map = undefined
    if (product?.cultivationPlace?.location?.latitude && product?.cultivationPlace?.location?.longitude) {
        map = {
            location: {
                latitude: product.cultivationPlace.location.latitude,
                longitude: product.cultivationPlace.location.longitude
            }
        }
    }

    return (
        <CardLayout
            content={() => {
                return (
                    <>
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
                    </>
                )
            }}
            map={map}
        />
    )
}

export default ProductContent