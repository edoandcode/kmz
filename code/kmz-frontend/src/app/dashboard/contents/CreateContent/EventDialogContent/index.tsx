'use client'
import React, { useEffect } from 'react';
import { Controller, useForm } from 'react-hook-form';

import { zodResolver } from '@hookform/resolvers/zod';
import { Session } from 'next-auth';
import { toast } from 'sonner';
import useSWR from 'swr';

import FormErrorMessage from '@/components/FormErrorMessage';
import FormPlaceItem from '@/components/FormPlaceItem';
import { Button } from '@/components/ui/button';
import { Datepicker } from '@/components/ui/datepicker';
import {
    DialogClose, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle
} from '@/components/ui/dialog';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import ValuesPicker from '@/components/ui/valuespicker';

import { get, post } from '@/services/api';
import { API, API_REFRESH_INTERVAL } from '@/settings/api';
import { UserDto } from '@/types/api/user/types';
import { eventSchema } from '@/validation/contents/event/schema';

import type { EventSchema } from '@/validation/contents/event/schema';
// user fetcher
const fetcher = (url: string, token?: string) =>
    get<UserDto[]>(url, {
        headers: { Authorization: `Bearer ${token}` },
    });

const EventDialogContent = ({ session }: { session: Session | null }) => {

    const { data: users } = useSWR(
        session ? [`/${API.USERS}`, session?.user?.accessToken] : null,
        ([url, token]) => fetcher(url, token),
        {
            revalidateOnFocus: true,
            revalidateOnReconnect: true,
            shouldRetryOnError: true,
            refreshInterval: API_REFRESH_INTERVAL, // Refresh every 15 seconds
        }
    );

    useEffect(() => {
        console.log('users', users);
    }, [users])


    const { handleSubmit, register, formState: { errors }, control } = useForm<EventSchema>({
        resolver: zodResolver(eventSchema),
    })

    const onSubmit = async (data: EventSchema) => {
        console.log(data);

        const eventDto = {
            name: data.name,
            description: data.description,
            place: data.place,
            date: data.date ? data.date.toLocaleDateString('en-CA') : null,
            guests: data.guests.map(email => users?.find(u => u.email === email)).filter(u => u !== undefined) as UserDto[],
        }

        console.log("Event DTO:", eventDto);

        try {
            const event = await post(`/${API.EVENTS}`, eventDto, {
                headers: {
                    Authorization: `Bearer ${session?.user?.accessToken}`
                }
            });

            if (event)
                toast.success("Event created successfully!");
            else
                toast.error("Failed to create event. Please try again.");

        } catch (error) {
            if (error instanceof Error)
                toast.error("Error creating event: " + error.message);
            else
                toast.error("An unexpected error occurred. Please try again later.");
        }

    }

    return (
        <form id="event-form" onSubmit={handleSubmit(onSubmit)}>
            <DialogContent className="sm:max-w-[425px]">
                <DialogHeader>
                    <DialogTitle>Crea Evento</DialogTitle>
                    <DialogDescription>
                        {"Inserisci le informazioni dell'evento qui. Clicca salva o pubblica quando hai finito."}
                    </DialogDescription>
                </DialogHeader>
                <p></p>
                <div className="grid gap-4">
                    <div className="grid gap-3 relative">
                        <Label htmlFor="name">Name</Label>
                        <Input
                            id="name"
                            placeholder="Nome Evento"
                            {...register("name")}
                        />
                        <FormErrorMessage
                            error={errors.name}
                        ></FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="description">Description</Label>
                        <Input
                            id="description"
                            placeholder="Descrizione Evento"
                            {...register("description")}
                        />
                        <FormErrorMessage
                            error={errors.description}
                        ></FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="place">Place</Label>
                        <Controller
                            control={control}
                            name="place"
                            render={({ field }) => (
                                <FormPlaceItem
                                    {...field}
                                />
                            )}
                        />
                        <FormErrorMessage
                            error={errors.date}
                        ></FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="date">Event Date</Label>
                        <div className="flex gap-2">
                            <Controller
                                control={control}
                                name="date"
                                render={({ field }) => (
                                    <Datepicker
                                        {...field}
                                    />
                                )}
                            />
                        </div>
                        <FormErrorMessage
                            error={errors.date}
                        ></FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="guests">Event Guests</Label>
                        <div className="flex gap-2">
                            <Controller
                                control={control}
                                name="guests"
                                render={({ field }) => (
                                    <ValuesPicker
                                        values={users?.map(u => u.email) || []}
                                        {...field}
                                    />
                                )}
                            />
                        </div>
                        <FormErrorMessage
                            error={errors.guests}
                        ></FormErrorMessage>
                    </div>
                </div>
                <DialogFooter>
                    <DialogClose asChild>
                        <Button variant="outline_primary">Cancel</Button>
                    </DialogClose>
                    <Button
                        variant="primary"
                        type="submit"
                        form="event-form"
                    >Save changes</Button>
                </DialogFooter>

            </DialogContent>
        </form >
    )
}

export default EventDialogContent