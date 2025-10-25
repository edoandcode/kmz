import { z } from 'zod';

import { processSchema } from '../process/schema';
import { productSchema } from '../product/schema';

export const processedProductSchema = z.object({
    name: z.string().min(1, { message: "Name is required" }),
    description: z.string().optional(),
    certifications: z.array(z.string()).optional(),
    processes: z.array(z.string()).min(1, { message: "At least one process is required" }),
    ingredients: z.array(z.string()).min(1, { message: "At least one ingredient is required" }),
})

export type ProcessedProductSchema = z.infer<typeof processedProductSchema>;