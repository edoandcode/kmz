import React from 'react';
import { useForm } from 'react-hook-form';

import { zodResolver } from '@hookform/resolvers/zod';

import FormErrorMessage from '@/components/FormErrorMessage';
import { Button } from '@/components/ui/button';
import { Datepicker } from '@/components/ui/datepicker';
import {
    DialogClose, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle
} from '@/components/ui/dialog';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';

import { productSchema } from '@/validation/contents/product/schema';

import type { ProductSchema } from '@/validation/contents/product/schema';
const ProductDialogContent = () => {

    const [sowingDate, setSowingDate] = React.useState<Date | null>(null);
    const [harvestDate, setHarvestDate] = React.useState<Date | null>(null);


    const { handleSubmit, register, formState: { errors } } = useForm<ProductSchema>({
        resolver: zodResolver(productSchema)
    })

    const onSubmit = (data: ProductSchema) => {
        console.log(data);
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
                            <Input
                                id="sowing-date"
                                placeholder="Sowing Date"
                                value={sowingDate?.toLocaleDateString()}
                                readOnly
                                {...register("sowingDate")}
                            />
                            <Datepicker onDateChange={(date) => setSowingDate(date)} />
                        </div>
                        <FormErrorMessage>{errors.sowingDate?.message}</FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="harvest-date">Harvest Date</Label>
                        <div className="flex gap-2">
                            <Input
                                id="harvest-date"
                                placeholder="Harvest Date"
                                value={harvestDate?.toLocaleDateString()}
                                readOnly
                                {...register("harvestDate")}
                            />
                            <Datepicker onDateChange={(date) => setHarvestDate(date)} />
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
                    <Button type="submit" form="product-form">Save changes</Button>
                </DialogFooter>
            </DialogContent>
        </form>
    )
}

export default ProductDialogContent