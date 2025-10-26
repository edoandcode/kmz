import React, { useEffect } from 'react';
import { Controller, useForm } from 'react-hook-form';

import { zodResolver } from '@hookform/resolvers/zod';
import _ from 'lodash';
import { Session } from 'next-auth';
import { toast } from 'sonner';

import FormErrorMessage from '@/components/FormErrorMessage';
import FormPlaceItem from '@/components/FormPlaceItem';
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

    useEffect(() => {
        console.log('form errors', errors);
    }, [errors])

    const onSubmit = async (data: ProductSchema) => {
        console.log(data);

        const productDto = {
            name: data.name,
            description: data.description,
            sowingDate: data.sowingDate ? data.sowingDate.toLocaleDateString('en-CA') : null,
            harvestDate: data.harvestDate ? data.harvestDate.toLocaleDateString('en-CA') : null,
            cultivationMethod: data.cultivationMethod,
            cultivationPlace: data.cultivationPlace || {},
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
                        Inserisci le informazioni del prodotto qui. Clicca salva quando hai finito.
                    </DialogDescription>
                </DialogHeader>
                <p></p>
                <div className="grid gap-4">
                    <div className="grid gap-3 relative">
                        <Label htmlFor="name">Name</Label>
                        <Input
                            id="name"
                            placeholder="Nome Prodotto"
                            {...register("name")}
                        />
                        <FormErrorMessage
                            error={errors.name}
                        ></FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="description">Description</Label>
                        <Textarea
                            id="description"
                            placeholder="Descrizione Prodotto"
                            {...register("description")}
                        />
                        <FormErrorMessage
                            error={errors.description}
                        ></FormErrorMessage>
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
                        <FormErrorMessage error={errors.sowingDate}></FormErrorMessage>
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
                        <FormErrorMessage error={errors.harvestDate}></FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="cultivation-method">Cultivation Method</Label>
                        <Textarea
                            id="cultivation-method"
                            placeholder="Metodo di Coltivazione"
                            {...register("cultivationMethod")}
                        />
                        <FormErrorMessage error={errors.cultivationMethod}></FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="cultivationPlace">Cultivation Place</Label>
                        <Controller
                            control={control}
                            name="cultivationPlace"
                            render={({ field }) => (
                                <FormPlaceItem
                                    {...field}
                                />
                            )}
                        />
                        <FormErrorMessage
                            error={errors.cultivationPlace}
                        ></FormErrorMessage>
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

export default ProductDialogContent