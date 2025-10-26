import React from 'react';
import { Controller, useForm } from 'react-hook-form';

import { zodResolver } from '@hookform/resolvers/zod';
import { Session } from 'next-auth';
import { toast } from 'sonner';
import useSWR from 'swr';

import FormErrorMessage from '@/components/FormErrorMessage';
import { Button } from '@/components/ui/button';
import {
    DialogClose, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle
} from '@/components/ui/dialog';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';
import ValuesPicker from '@/components/ui/valuespicker';

import { get, post } from '@/services/api';
import { API, API_REFRESH_INTERVAL } from '@/settings/api';
import { ProcessContentDto, ProductContentDto } from '@/types/api/content/types';
import { processedProductSchema } from '@/validation/contents/processedProduct/schema';

import type { ProcessedProductSchema } from '@/validation/contents/processedProduct/schema';

// product fetcher
const productFetcher = (url: string, token?: string) =>
    get<ProductContentDto[]>(url, {
        headers: { Authorization: `Bearer ${token}` },
    });

// process fetcher
const processFetcher = (url: string, token?: string) =>
    get<ProcessContentDto[]>(url, {
        headers: { Authorization: `Bearer ${token}` },
    });

const ProcessedProductDialogContent = ({ session }: { session: Session | null }) => {

    const { data: products } = useSWR(
        session ? [`/${API.PRODUCTS}`, session?.user?.accessToken] : null,
        ([url, token]) => productFetcher(url, token),
        {
            revalidateOnFocus: true,
            revalidateOnReconnect: true,
            shouldRetryOnError: true,
            refreshInterval: API_REFRESH_INTERVAL, // Refresh every 15 seconds
        }
    );

    const { data: processes } = useSWR(
        session ? [`/${API.MY_PROCESSES}`, session?.user?.accessToken] : null,
        ([url, token]) => processFetcher(url, token),
        {
            revalidateOnFocus: true,
            revalidateOnReconnect: true,
            shouldRetryOnError: true,
            refreshInterval: API_REFRESH_INTERVAL, // Refresh every 15 seconds
        }
    );

    const { handleSubmit, register, formState: { errors }, control } = useForm<ProcessedProductSchema>({
        resolver: zodResolver(processedProductSchema)
    })

    const onSubmit = async (data: ProcessedProductSchema) => {
        console.log("Processed Product Data:", data);

        const processedProductDto = {
            name: data.name,
            description: data.description,
            processes: data.processes.map(name => processes?.find((p) => p.name === name)).filter(p => p !== undefined) as ProcessContentDto[],
            ingredients: data.ingredients.map(name => products?.find((p) => p.name === name)).filter(p => p !== undefined) as ProductContentDto[],
        }

        console.log("ProcessedProduct DTO:", processedProductDto);

        try {
            const product = await post(`/${API.PROCESSED_PRODUCT}`, processedProductDto, {
                headers: {
                    Authorization: `Bearer ${session?.user?.accessToken}`
                }
            });

            if (product)
                toast.success("Product created successfully!");
            else
                toast.error("Failed to create product. Please try again.");

        } catch (error) {
            if (error instanceof Error)
                toast.error("Error creating product: " + error.message);
            else
                toast.error("An unexpected error occurred. Please try again later.");
        }

    }

    return (
        <form id="product-form" onSubmit={handleSubmit(onSubmit)}>
            <DialogContent className="sm:max-w-[425px]">
                <DialogHeader>
                    <DialogTitle>Crea Prodotto</DialogTitle>
                    <DialogDescription>
                        Inserisci le informazioni del prodotto qui. Clicca salva o pubblica quando hai finito.
                    </DialogDescription>
                </DialogHeader>
                <p></p>
                <div className="grid gap-4">
                    <div className="grid gap-3 relative">
                        <Label htmlFor="name">Name</Label>
                        <Input
                            id="name"
                            defaultValue="Nome Prodotto Lavorato"
                            {...register("name")}
                        />
                        <FormErrorMessage>{errors.name?.message}</FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="description">Description</Label>
                        <Textarea
                            id="description"
                            defaultValue="Descrizione Prodotto Lavorato"
                            {...register("description")}
                        />
                        <FormErrorMessage>{errors.description?.message}</FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="processes">Event Processes</Label>
                        <div className="flex gap-2">
                            <Controller
                                control={control}
                                name="processes"
                                render={({ field }) => (
                                    <ValuesPicker
                                        values={processes?.map(p => p.name) || []}
                                        {...field}
                                    />
                                )}
                            />
                        </div>
                        <FormErrorMessage>{errors.processes?.message}</FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="ingredients">Event Ingredients</Label>
                        <div className="flex gap-2">
                            <Controller
                                control={control}
                                name="ingredients"
                                render={({ field }) => (
                                    <ValuesPicker
                                        values={products?.map(p => p.name) || []}
                                        {...field}
                                    />
                                )}
                            />
                        </div>
                        <FormErrorMessage>{errors.ingredients?.message}</FormErrorMessage>
                    </div>
                </div>
                <DialogFooter>
                    <DialogClose asChild>
                        <Button variant="outline_primary">Cancel</Button>
                    </DialogClose>
                    <Button
                        variant="primary"
                        type="submit"
                        form="product-form"
                    >Save changes</Button>
                </DialogFooter>
            </DialogContent>
        </form>
    )
}

export default ProcessedProductDialogContent