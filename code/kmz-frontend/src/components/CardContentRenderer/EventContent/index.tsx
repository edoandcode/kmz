import React from 'react';

import { CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';

import { Content, EventContent as EventContentType } from '@/types/api/content/types';

const EventContent = ({ content }: { content: Content }) => {

    const event = content as EventContentType;

    return (
        <>
            <CardTitle>{event.name}</CardTitle>
            <p className='font-medium '>{'Luogo'}</p>
            <CardDescription>{event.location}</CardDescription>
        </>
    )
}

export default EventContent