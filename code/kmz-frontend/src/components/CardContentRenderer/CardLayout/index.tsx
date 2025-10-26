import React from 'react';

import OpenLayersMap from '@/components/OpenLayerMap';

const CardLayout = ({ content, map }: { content: React.FC, map?: { location: { latitude: number, longitude: number } } }) => {

    const ContentComponent = content;

    return (
        <div className="flex h-full md:flex-row flex-col gap-y-5">
            <div className="md:w-[calc(50%-10px)]">
                <ContentComponent></ContentComponent>
            </div>
            {map?.location ? (
                <div className="md:w-[calc(50%-10px)] max-h-full">
                    <div
                        className="md:w-full h-full"
                    >
                        <OpenLayersMap
                            lat={map.location?.latitude}
                            lon={map.location?.longitude}
                            zoom={10}
                        ></OpenLayersMap>
                    </div>
                </div>
            ) : null}
        </div>
    )
}

export default CardLayout