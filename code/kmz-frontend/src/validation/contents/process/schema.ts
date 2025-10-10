import { z } from 'zod';

export const processSchema = z.object({
    name: z.string().min(1, { message: "Name is required" }),
    description: z.string(),
    certifications: z.string().optional(),
});

export type ProcessSchema = z.infer<typeof processSchema>;