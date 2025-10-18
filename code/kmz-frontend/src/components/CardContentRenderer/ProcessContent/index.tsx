import React from 'react';

import { CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';

import { Content, ProcessContent as ProcessContentType } from '@/types/api/content/types';

const ProcessContent = ({ content }: { content: Content }) => {

    const process = content as ProcessContentType;

    return (
        <>
            <CardTitle>{process.name}</CardTitle>
            <p className='font-medium '>{'Descrizione'}</p>
            <CardDescription>{process.description}</CardDescription>
        </>
    )
}

export default ProcessContent