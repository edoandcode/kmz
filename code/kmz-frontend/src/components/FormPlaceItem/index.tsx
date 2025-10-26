'use client';
import React, { useEffect } from 'react';

import { Card, CardContent } from '@/components/ui/card';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';

import { Place } from '@/types/api/place/types';

const FormPlaceItem = ({ onChange }: { onChange: (place: Place | null) => void }) => {

    const [place, setPlace] = React.useState<Place | null>(null);

    useEffect(() => {
        onChange(place)
    }, [place, onChange])


    return (
        <Card  >
            <CardContent className="flex flex-col gap-2">
                <Label htmlFor="name">Name</Label>
                <Input
                    id="name"
                    defaultValue="Nome Luogo"
                    onChange={(e) => setPlace({ ...place, name: e.target.value } as Place)}
                />
                <Label htmlFor="description">Description</Label>
                <Textarea
                    id="description"
                    defaultValue="Descrizione Luogo"
                    onChange={(e) => setPlace({ ...place, description: e.target.value } as Place)}
                />
                <Label>Location</Label>
                <div className="flex gap-2 items-center">
                    <small>{"latitudine"}</small>
                    <Input
                        id="latitude"
                        type="number"
                        placeholder="Latitude"
                        onChange={(e) => setPlace({
                            ...place,
                            location: {
                                ...place?.location,
                                latitude: parseFloat(e.target.value)
                            }
                        } as Place)}
                    />
                </div>
                <div className="flex gap-2 items-center">
                    <small>{"longitudine"}</small>
                    <Input
                        id="longitude"
                        type="number"
                        placeholder="Longitude"
                        onChange={(e) => setPlace({
                            ...place,
                            location: {
                                ...place?.location,
                                longitude: parseFloat(e.target.value)
                            }
                        } as Place)}
                    />
                </div>
            </CardContent>
        </Card >
    )
}

export default FormPlaceItem