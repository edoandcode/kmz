import React from 'react';

import { CardDescription, CardTitle } from '@/components/ui/card';

import { Content, ProcessContent as ProcessContentType } from '@/types/api/content/types';

import CardLayout from '../CardLayout';

const ProcessContent = ({ content }: { content: Content }) => {

    const process = content as ProcessContentType;

    let map = undefined
    if (process.processingPlace?.location?.latitude && process.processingPlace?.location?.longitude) {
        map = {
            location: {
                latitude: process.processingPlace.location.latitude,
                longitude: process.processingPlace.location.longitude
            }
        }
    }

    return (
        <CardLayout
            content={() => {
                return (
                    <>
                        <div className="flex flex-col gap-2 mb-3">
                            <CardTitle>{process.name}</CardTitle>
                            <CardDescription>{process.description}</CardDescription>
                        </div>
                        <small className='font-medium '>{'Luogo di lavorazione'}</small>
                        <CardDescription>{process.processingPlace?.name}</CardDescription>
                    </>
                )
            }}

            map={map}
        />
    )
}

export default ProcessContent