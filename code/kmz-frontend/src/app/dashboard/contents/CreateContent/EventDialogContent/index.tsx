import React from 'react';
import { Controller, useForm } from 'react-hook-form';

import { zodResolver } from '@hookform/resolvers/zod';
import { Session } from 'next-auth';
import { toast } from 'sonner';

import FormErrorMessage from '@/components/FormErrorMessage';
import { Button } from '@/components/ui/button';
import { Datepicker } from '@/components/ui/datepicker';
import {
    DialogClose, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle
} from '@/components/ui/dialog';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';
import ValuesPicker from '@/components/ui/valuepicker';

import { post } from '@/services/api';
import { API } from '@/settings/api';
import { eventSchema } from '@/validation/contents/event/schema';

import type { EventSchema } from '@/validation/contents/event/schema';
const EventDialogContent = ({ session }: { session: Session | null }) => {

    const { handleSubmit, register, formState: { errors }, control } = useForm<EventSchema>({
        resolver: zodResolver(eventSchema),
    })

    const onSubmit = async (data: EventSchema) => {
        console.log(data);

        const eventDto = {
            name: data.name,
            description: data.description,
            location: data.location,
            date: data.date ? data.date.toLocaleDateString('en-CA') : null,
            guestsIds: data.guestsIds || [],
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
                            defaultValue="Nome Evento"
                            {...register("name")}
                        />
                        <FormErrorMessage>{errors.name?.message}</FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="description">Description</Label>
                        <Input
                            id="description"
                            defaultValue="Descrizione Evento"
                            {...register("description")}
                        />
                        <FormErrorMessage>{errors.description?.message}</FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="location">Location</Label>
                        <Input
                            id="location"
                            defaultValue="Luogo Evento"
                            {...register("location")}
                        />
                        <FormErrorMessage>{errors.location?.message}</FormErrorMessage>
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
                        <FormErrorMessage>{errors.date?.message}</FormErrorMessage>
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
                <ValuesPicker
                    label="Event Values"
                    values={[
                        "Mario",
                        "Paolo",
                        "Jacopo"
                    ]}
                    onChange={(value) => console.log(value)}
                />
            </DialogContent>
        </form >
    )
}

export default EventDialogContent