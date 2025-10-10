import { z } from 'zod';

export const productSchema = z.object({
    name: z.string().min(1, { message: "Name is required" }),
    description: z.string(),
    sowingDate: z.date(),
    harvestDate: z.date(),
    cultivationMethod: z.string(),
});

export type ProductSchema = z.infer<typeof productSchema>;

