'use client'
import React, { useState } from 'react';

import { Button } from '@/components/ui/button';
import {
    Dialog, DialogClose, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle
} from '@/components/ui/dialog';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';

import { ContentType } from '@/types/api/data-types';

import CreateContentButtons from './CreateContentButtons';
import ProductDialogContent from './ProductDialogContent';

const CreateContent = () => {

    const [currentContentType, setCurrentContentType] = useState<ContentType | null>(null);

    return (
        <div>
            <Dialog>
                <CreateContentButtons setContentType={setCurrentContentType} />
                {currentContentType === ContentType.PRODUCT ? (
                    <ProductDialogContent />
                ) : null}
            </Dialog>
        </div>
    )
}

export default CreateContent