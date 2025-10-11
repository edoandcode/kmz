'use client'
import React, { useState } from 'react';

import { Session, User } from 'next-auth';

import { Dialog } from '@/components/ui/dialog';

import { ContentType } from '@/types/api/content/types';

import CreateContentButtons from './CreateContentButtons';
import EventDialogContent from './EventDialogContent';
import ProcessDialogContent from './ProcessDialogContent';
import ProductDialogContent from './ProductDialogContent';

const CreateContent = ({ session }: { session: Session | null }) => {

    const [currentContentType, setCurrentContentType] = useState<ContentType | null>(null);

    return (
        <div>
            <Dialog>
                <CreateContentButtons setContentType={setCurrentContentType} />
                {currentContentType === ContentType.PRODUCT ? (
                    <ProductDialogContent session={session} />
                ) : null}
                {currentContentType === ContentType.EVENT ? (
                    <EventDialogContent session={session} />
                ) : null}
                {currentContentType === ContentType.PROCESS ? (
                    <ProcessDialogContent session={session} />
                ) : null}
            </Dialog>
        </div>
    )
}

export default CreateContent