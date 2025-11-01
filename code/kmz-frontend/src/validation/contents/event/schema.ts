import { z } from 'zod';

import { placeSchema } from '@/validation/place/schema';

export const eventSchema = z.object({
    name: z.string().min(1, { message: "Name is required" }),
    description: z.string().min(1, { message: "Description is required" }),
    place: placeSchema,
    date: z.date(),
    guests: z.array(z.email()),

});

export type EventSchema = z.infer<typeof eventSchema>;