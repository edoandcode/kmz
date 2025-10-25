import { z } from 'zod';

import { userSchema } from '@/validation/user/schema';

export const eventSchema = z.object({
    name: z.string().min(1, { message: "Name is required" }),
    description: z.string().min(1, { message: "Description is required" }),
    location: z.string().min(1, { message: "Location is required" }),
    date: z.date(),
    guests: z.array(z.email()).min(1),

});

export type EventSchema = z.infer<typeof eventSchema>;