import React from 'react';
import { useForm } from 'react-hook-form';

import { zodResolver } from '@hookform/resolvers/zod';
import { Session } from 'next-auth';
import { toast } from 'sonner';

import FormErrorMessage from '@/components/FormErrorMessage';
import { Button } from '@/components/ui/button';
import {
    DialogClose, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle
} from '@/components/ui/dialog';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';

import { post } from '@/services/api';
import { API } from '@/settings/api';
import { processSchema } from '@/validation/contents/process/schema';

import type { ProcessSchema } from '@/validation/contents/process/schema';


const ProcessDialogContent = ({ session }: { session: Session | null }) => {

    const { handleSubmit, register, formState: { errors } } = useForm<ProcessSchema>({
        resolver: zodResolver(processSchema)
    })

    const onSubmit = async (data: ProcessSchema) => {
        console.log(data);

        const processDto = {
            name: data.name,
            description: data.description,
            certifications: data.certifications || [],
        }

        console.log("Process DTO:", processDto);

        try {
            const process = await post(`/${API.PROCESS}`, processDto, {
                headers: {
                    Authorization: `Bearer ${session?.user?.accessToken}`
                }
            });

            if (process)
                toast.success("Process created successfully!");
            else
                toast.error("Failed to create process. Please try again.");

        } catch (error) {
            if (error instanceof Error)
                toast.error("Error creating process: " + error.message);
            else
                toast.error("An unexpected error occurred. Please try again later.");
        }

    }

    return (
        <form id="process-form" onSubmit={handleSubmit(onSubmit)}>
            <DialogContent className="sm:max-w-[425px]">
                <DialogHeader>
                    <DialogTitle>Crea Processo</DialogTitle>
                    <DialogDescription>
                        Inserisci le informazioni del processo qui. Clicca salva o pubblica quando hai finito.
                    </DialogDescription>
                </DialogHeader>
                <p></p>
                <div className="grid gap-4">
                    <div className="grid gap-3 relative">
                        <Label htmlFor="name">Name</Label>
                        <Input
                            id="name"
                            defaultValue="Nome Processo"
                            {...register("name")}
                        />
                        <FormErrorMessage>{errors.name?.message}</FormErrorMessage>
                    </div>
                    <div className="grid gap-3 relative">
                        <Label htmlFor="description">Description</Label>
                        <Textarea
                            id="description"
                            defaultValue="Descrizione Processo"
                            {...register("description")}
                        />
                        <FormErrorMessage>{errors.description?.message}</FormErrorMessage>
                    </div>
                </div>
                <DialogFooter>
                    <DialogClose asChild>
                        <Button variant="outline_primary">Cancel</Button>
                    </DialogClose>
                    <Button
                        variant="primary"
                        type="submit"
                        form="process-form"
                    >Save changes</Button>
                </DialogFooter>
            </DialogContent>
        </form>
    )
}

export default ProcessDialogContent