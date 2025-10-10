import React from 'react';
import { Controller, useForm } from 'react-hook-form';

import { zodResolver } from '@hookform/resolvers/zod';
import { Session } from 'next-auth';
import { toast } from 'sonner';

import FormErrorMessage from '@/components/FormErrorMessage';
import { Button } from '@/components/ui/button';
import { Datepicker } from '@/components/ui/datepicker';
import {
    DialogClose, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle
} from '@/components/ui/dialog';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';

import { post } from '@/services/api';
import { API } from '@/settings/api';
import { productSchema } from '@/validation/contents/product/schema';

import type { ProductSchema } from '@/validation/contents/product/schema';


const ProductDialogContent = ({ session }: { session: Session | null }) => {

    const { handleSubmit, register, formState: { errors }, control } = useForm<ProductSchema>({
        resolver: zodResolver(productSchema)
    })

    const onSubmit = async (data: ProductSchema) => {
        console.log(data);

        const productDto = {
            name: data.name,
            description: data.description,
            sowingDate: data.sowingDate ? data.sowingDate.toLocaleDateString('en-CA') : null,
            harvestDate: data.harvestDate ? data.harvestDate.toLocaleDateString('en-CA') : null,
            cultivationMethod: data.cultivationMethod,
        }

        console.log("Product DTO:", productDto);

        try {
            const product = await post(`/${API.PRODUCTS}`, productDto, {
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
                            defaultValue="Nome Prodotto"
                            {...register("name")}
                        />
                        <FormErrorMessage>{errors.name?.message}</FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="description">Description</Label>
                        <Textarea
                            id="description"
                            defaultValue="Descrizione Prodotto"
                            {...register("description")}
                        />
                        <FormErrorMessage>{errors.description?.message}</FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="sowing-date">Sowing Date</Label>
                        <div className="flex gap-2">
                            <Controller
                                control={control}
                                name="sowingDate"
                                render={({ field }) => (
                                    <Datepicker
                                        {...field}
                                    />
                                )}
                            />
                        </div>
                        <FormErrorMessage>{errors.sowingDate?.message}</FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="harvest-date">Harvest Date</Label>
                        <div className="flex gap-2">
                            <Controller
                                control={control}
                                name="harvestDate"
                                render={({ field }) => (
                                    <Datepicker
                                        {...field}
                                    />
                                )}
                            />
                        </div>
                        <FormErrorMessage>{errors.harvestDate?.message}</FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="cultivation-method">Cultivation Method</Label>
                        <Textarea
                            id="cultivation-method"
                            defaultValue="Metodo di Coltivazione"
                            {...register("cultivationMethod")}
                        />
                        <FormErrorMessage>{errors.cultivationMethod?.message}</FormErrorMessage>
                    </div>
                </div>
                <DialogFooter>
                    <DialogClose asChild>
                        <Button variant="outline">Cancel</Button>
                    </DialogClose>
                    <Button
                        variant="secondary"
                        type="submit"
                        form="product-form"
                    >Save changes</Button>
                </DialogFooter>
            </DialogContent>
        </form>
    )
}

export default ProductDialogContent