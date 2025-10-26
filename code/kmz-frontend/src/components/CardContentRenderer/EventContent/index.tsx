import React from 'react';

import KeyValueItem from '@/components/KeyValueItem';
import OpenLayersMap from '@/components/OpenLayerMap';
import { CardDescription, CardTitle } from '@/components/ui/card';

import { Content, EventContent as EventContentType } from '@/types/api/content/types';

const EventContent = ({ content }: { content: Content }) => {

    const event = content as EventContentType;

    return (
        <div className="flex h-full">
            <div className="w-[calc(50%-10px)]">
                <div className="flex flex-col gap-2 mb-3">
                    <CardTitle>{event.name}</CardTitle>
                    <CardDescription>{event.description}</CardDescription>
                </div>
                <KeyValueItem label='Data' value={new Date(event.date).toLocaleDateString()} />
                <KeyValueItem label='Ospiti' value={event.guests ? event.guests.map(guest => `${guest.firstName} ${guest.lastName}`).join(', ') : 'Nessun ospite'} />
            </div>
            {event.place ? (
                <div className="w-[calc(50%-10px)] max-h-full">
                    <div
                        className="w-full h-full bg-neutral-600"
                    >
                        <OpenLayersMap
                            lat={event.place?.location?.latitude}
                            lon={event.place?.location?.longitude}
                            zoom={10}
                        ></OpenLayersMap>
                    </div>
                </div>
            ) : null}
        </div>
    )
}

export default EventContent