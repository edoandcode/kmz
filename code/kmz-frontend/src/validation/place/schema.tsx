import { z } from 'zod';

export const locationSchema = z.object({
    latitude: z.number().min(-90).max(90, { message: "Latitude must be between -90 and 90" }),
    longitude: z.number().min(-180).max(180, { message: "Longitude must be between -180 and 180" }),
});

export type LocationSchema = z.infer<typeof locationSchema>;

export const placeSchema = z.object({
    name: z.string().min(1, { message: "Name is required" }),
    description: z.string().min(1, { message: "Description is required" }),
    location: locationSchema,
});