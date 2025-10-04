'use client'
import { useForm } from 'react-hook-form';

import { zodResolver } from '@hookform/resolvers/zod';
import { useRouter } from 'next/navigation';

import FormErrorMessage from '@/components/FormErrorMessage';
import { Button } from '@/components/ui/button';
import {
    Card, CardAction, CardContent, CardDescription, CardFooter, CardHeader, CardTitle
} from '@/components/ui/card';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import {
    Select, SelectContent, SelectItem, SelectTrigger, SelectValue
} from '@/components/ui/select';

import { post } from '@/services/api';
import { ROUTES } from '@/settings/routes';
import { UserRole } from '@/types/api/data-types';
import { signUpSchema } from '@/validation/user/schema';

import type { UserDto } from '@/types/api/data-types';
import type { RegisterUserDto } from '@/types/api/data-types';
import type { SignUpSchema } from '@/validation/user/schema';



interface SignUpFormProps {
    isSuperAdminSetup?: boolean;
}

export function SignupForm({ isSuperAdminSetup = false }: SignUpFormProps) {

    const router = useRouter();

    const { register, handleSubmit, formState: { errors } } = useForm<SignUpSchema>({
        resolver: zodResolver(signUpSchema)
    });


    const onSubmit = async (data: SignUpSchema) => {
        console.log(data);

        const registerUserData: RegisterUserDto = {
            firstName: data.firstName,
            lastName: data.lastName,
            email: data.email,
            password: data.password,
            roles: isSuperAdminSetup ? Object.values(UserRole) : data.roles ? [data.roles] : []
        }


        try {
            const userDto: UserDto = await post<UserDto>('/users/setup-admin', registerUserData);

            router.push(`/${ROUTES.DASHBOARD}`);

            console.log('userDto', userDto);

        } catch (error) {
            console.log(error);
        }
    }


    return (
        <Card className="w-full max-w-sm">
            <CardHeader>
                <CardTitle>Create an account</CardTitle>
                <CardDescription>
                    Fill in the details below to register a new account
                </CardDescription>
                <CardAction>
                    <Button variant="link">Login</Button>
                </CardAction>
            </CardHeader>
            <CardContent>
                <form
                    id="signup-form"
                    onSubmit={handleSubmit(onSubmit)}
                >
                    <div className="flex flex-col gap-6">
                        <div className="grid gap-2 relative">
                            <Label htmlFor="firstName">First Name</Label>
                            <Input
                                id="firstName"
                                type="text"
                                placeholder="John"
                                {...register('firstName')}
                            />
                            {errors.firstName ? (
                                <FormErrorMessage>{errors.firstName.message}</FormErrorMessage>
                            ) : null}
                        </div>

                        <div className="grid gap-2 relative">
                            <Label htmlFor="lastName">Last Name</Label>
                            <Input
                                id="lastName"
                                type="text"
                                placeholder="Doe"
                                {...register('lastName')}
                            />
                            {errors.lastName ? (
                                <FormErrorMessage>{errors.lastName.message}</FormErrorMessage>
                            ) : null}
                        </div>

                        <div className="grid gap-2 relative">
                            <Label htmlFor="email">Email</Label>
                            <Input
                                id="email"
                                type="email"
                                placeholder="m@example.com"
                                {...register('email')}
                            />
                            {errors.email ? (
                                <FormErrorMessage>{errors.email.message}</FormErrorMessage>
                            ) : null}
                        </div>

                        <div className="grid gap-2 relative">
                            <Label htmlFor="password">Password</Label>
                            <Input
                                id="password"
                                type="password"
                                {...register('password')}
                            />
                            {errors.password ? (
                                <FormErrorMessage>{errors.password.message}</FormErrorMessage>
                            ) : null}
                        </div>

                        <div className="grid gap-2 relative">
                            <Label htmlFor="confirmPassword">Confirm Password</Label>
                            <Input
                                id="confirmPassword"
                                type="password"
                                {...register('confirmPassword')}
                            />
                            {errors.confirmPassword ? (
                                <FormErrorMessage>{errors.confirmPassword.message}</FormErrorMessage>
                            ) : null}
                        </div>
                        {!isSuperAdminSetup ? (
                            <div className="grid gap-2">
                                <Label htmlFor="role">Role</Label>
                                <Select>
                                    <SelectTrigger id="role">
                                        <SelectValue placeholder="Select a role" />
                                    </SelectTrigger>
                                    <SelectContent>
                                        {Object.values(UserRole).map((role) => (
                                            <SelectItem key={role} value={role}>{role}</SelectItem>
                                        ))}
                                    </SelectContent>
                                </Select>
                            </div>
                        ) : null}
                    </div>
                </form>
            </CardContent>
            <CardFooter className="flex-col gap-2">
                <Button
                    type="submit"
                    className="w-full"
                    form="signup-form"
                >
                    Sign Up
                </Button>
            </CardFooter>
        </Card>
    )
}

export default SignupForm
