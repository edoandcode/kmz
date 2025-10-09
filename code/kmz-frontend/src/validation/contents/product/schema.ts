import { z } from 'zod';

export const productSchema = z.object({
    name: z.string().min(1, { message: "Name is required" }),
    description: z.string(),
    sowingDate: z.string().regex(/^(?:$|(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4})$/, { message: "Invalid date format. Use DD/MM/YYYY" }).optional(),
    harvestDate: z.string().regex(/^(?:$|(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4})$/, { message: "Invalid date format. Use DD/MM/YYYY" }).optional(),
    cultivationMethod: z.string(),
});

export type ProductSchema = z.infer<typeof productSchema>;

