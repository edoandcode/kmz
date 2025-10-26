import React from 'react';

import { CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';

import { Content, ProcessContent as ProcessContentType } from '@/types/api/content/types';

const ProcessContent = ({ content }: { content: Content }) => {

    const process = content as ProcessContentType;

    return (
        <>
            <CardTitle>{process.name}</CardTitle>
            <small className='font-medium '>{'Descrizione'}</small>
            <CardDescription>{process.description}</CardDescription>
            <small className='font-medium '>{'Luogo di lavorazione'}</small>
            <CardDescription>{process.processingPlace?.name}</CardDescription>
        </>
    )
}

export default ProcessContent