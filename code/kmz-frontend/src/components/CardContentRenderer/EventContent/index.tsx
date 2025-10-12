import React from 'react';

import { CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';

import { Content, EventContent as EventContentType } from '@/types/api/content/types';

const EventContent = ({ content }: { content: Content }) => {

    const event = content as EventContentType;

    return (
        <>
            <CardHeader>
                <CardTitle>{event.name}</CardTitle>
            </CardHeader>
            <CardContent className='text-sm'>
                <p className='font-medium '>{'Luogo'}</p>
                <CardDescription>{event.location}</CardDescription>
            </CardContent>
        </>
    )
}

export default EventContent