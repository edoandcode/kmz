import React from 'react';

import { CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';

import { Content, ProcessContent as ProcessContentType } from '@/types/api/content/types';

const ProcessContent = ({ content }: { content: Content }) => {

    const process = content as ProcessContentType;

    return (
        <>
            <CardHeader>
                <CardTitle>{process.name}</CardTitle>
            </CardHeader>
            <CardContent className='text-sm'>
                <p className='font-medium '>{'Descrizione'}</p>
                <CardDescription>{process.description}</CardDescription>
            </CardContent>
        </>
    )
}

export default ProcessContent