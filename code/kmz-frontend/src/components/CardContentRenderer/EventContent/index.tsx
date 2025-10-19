import React from 'react';

import KeyValueItem from '@/components/KeyValueItem';
import { CardDescription, CardTitle } from '@/components/ui/card';

import { Content, EventContent as EventContentType } from '@/types/api/content/types';

const EventContent = ({ content }: { content: Content }) => {

    const event = content as EventContentType;

    return (
        <>
            <CardTitle>{event.name}</CardTitle>
            <CardDescription>{event.description}</CardDescription>
            <KeyValueItem label='Data' value={new Date(event.date).toLocaleDateString()} />
            <KeyValueItem label='Luogo' value={event.location} />
        </>
    )
}

export default EventContent