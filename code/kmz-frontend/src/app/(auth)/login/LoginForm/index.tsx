'use client'
import { useForm } from 'react-hook-form';

import { zodResolver } from '@hookform/resolvers/zod';
import { signIn } from 'next-auth/react';
import Link from 'next/link';
import { useRouter } from 'next/navigation';

import FormErrorMessage from '@/components/FormErrorMessage';
import { Button } from '@/components/ui/button';
import {
    Card, CardAction, CardContent, CardDescription, CardFooter, CardHeader, CardTitle
} from '@/components/ui/card';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';

import { ROUTES } from '@/settings/routes';
import { loginSchema, LoginSchema } from '@/validation/user/schema';

export function LoginForm() {


    const router = useRouter();


    const { register, handleSubmit, formState: { errors } } = useForm<LoginSchema>({
        resolver: zodResolver(loginSchema)
    });




    const onSubmit = async (data: LoginSchema) => {
        console.log(data);

        const registerUserData = {
            email: data.email,
            password: data.password,
        }

        const result = await signIn("credentials", {
            redirect: false,
            ...registerUserData
        })

        console.log('result', result)

        if (result?.error) {
            console.error(result.error)
        } else {
            router.push(`/${ROUTES.DASHBOARD}`);
            console.log("Login successful")
        }
    }


    return (
        <Card className="w-full max-w-sm">
            <CardHeader>
                <CardTitle>Login to your account</CardTitle>
                <CardDescription>
                    Enter your email below to login to your account
                </CardDescription>
                <CardAction>
                    <Button variant="link" asChild>
                        <Link href={`/${ROUTES.SIGNUP}`}>Sign Up</Link></Button>
                </CardAction>
            </CardHeader>
            <CardContent>
                <form id="login-form" onSubmit={handleSubmit(onSubmit)}>
                    <div className="flex flex-col gap-6">
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
                            <div className="flex items-center">
                                <Label htmlFor="password">Password</Label>
                            </div>
                            <Input
                                id="password"
                                type="password"
                                {...register('password')}
                            />
                            {errors.password ? (
                                <FormErrorMessage>{errors.password.message}</FormErrorMessage>
                            ) : null}
                        </div>
                    </div>
                </form>
            </CardContent>
            <CardFooter className="flex-col gap-2">
                <Button type="submit" form="login-form" className="w-full">
                    Login
                </Button>
            </CardFooter>
        </Card>
    )
}


export default LoginForm