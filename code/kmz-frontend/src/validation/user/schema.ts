import z from 'zod';

import { UserRole } from '@/types/api/user/types';

export const signUpSchema = z.object({
    firstName: z.string().min(3, 'First name must be at least 3 characters long'),
    lastName: z.string().min(3, 'Last name must be at least 3 characters long'),
    email: z.email('Invalid email address'),
    password: z.string().min(6, 'Password must be at least 6 characters long'),
    confirmPassword: z.string().min(6, 'Confirm Password must be at least 6 characters long'),
    roles: z.enum(Object.values(UserRole)).optional(),
}).refine((data) => data.password === data.confirmPassword, {
    message: "Passwords don't match",
    path: ['confirmPassword'],
})

export type SignUpSchema = z.infer<typeof signUpSchema>;



export const loginSchema = z.object({
    email: z.string().email('Invalid email address'),
    password: z.string().min(6, 'Password must be at least 6 characters long'),
});

export type LoginSchema = z.infer<typeof loginSchema>;