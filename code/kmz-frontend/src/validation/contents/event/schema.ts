import { z } from 'zod';

export const eventSchema = z.object({
    name: z.string().min(1, { message: "Name is required" }),
    description: z.string().min(1, { message: "Description is required" }),
    location: z.string().min(1, { message: "Location is required" }),
    date: z.date(),
    guestsIds: z.array(z.string()).optional(),

});

export type EventSchema = z.infer<typeof eventSchema>;