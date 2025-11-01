import { z } from 'zod';

import { placeSchema } from '@/validation/place/schema';

export const processSchema = z.object({
    name: z.string().min(1, { message: "Name is required" }),
    description: z.string(),
    certifications: z.string().optional(),
    processingPlace: placeSchema.nullable().optional(),
});

export type ProcessSchema = z.infer<typeof processSchema>;