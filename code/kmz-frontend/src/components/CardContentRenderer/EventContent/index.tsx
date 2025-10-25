import React from 'react';

import KeyValueItem from '@/components/KeyValueItem';
import { CardDescription, CardTitle } from '@/components/ui/card';

import { Content, EventContent as EventContentType } from '@/types/api/content/types';

const EventContent = ({ content }: { content: Content }) => {

    const event = content as EventContentType;

    return (
        <>
            <div className="flex flex-col gap-2 mb-3">
                <CardTitle>{event.name}</CardTitle>
                <CardDescription>{event.description}</CardDescription>
            </div>
            <KeyValueItem label='Data' value={new Date(event.date).toLocaleDateString()} />
            <KeyValueItem label='Luogo' value={event.location} />
            <KeyValueItem label='Ospiti' value={event.guests ? event.guests.map(guest => `${guest.firstName} ${guest.lastName}`).join(', ') : 'Nessun ospite'} />
        </>
    )
}

export default EventContent