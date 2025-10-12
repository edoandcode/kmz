import React, { useCallback } from 'react';

import { Session } from 'next-auth';
import { toast } from 'sonner';

import CardContentRenderer from '@/components/CardContentRenderer';
import { Button } from '@/components/ui/button';
import { Card, CardFooter } from '@/components/ui/card';

import { post } from '@/services/api';
import { API } from '@/settings/api';
import { Content, ContentStatus } from '@/types/api/content/types';
import { ContentPublicationResponseDto } from '@/types/api/request/types';

const ContentCard = ({ content, session }: { content: Content, session: Session | null }) => {

    const handlePublicationRequest = useCallback(async () => {
        // Handle publication request

        console.log('sesssion', session);

        try {
            const response = await post<ContentPublicationResponseDto>(`/${API.REQUEST_PUBLISH_CONTENT}/${content.id}`, {}, {
                headers: {
                    Authorization: `Bearer ${session?.user?.accessToken}`
                }
            })
            console.log('Publication request response:', response);
            toast.success('Publication request created successfully');
        } catch (error) {
            console.error('Error creating publication request:', error);
            toast.error('Error creating publication request');
        }
    }, [session, content])

    return (
        <Card className='basis-[calc(50%-1rem)] h-full'>
            <CardContentRenderer content={content} />
            <CardFooter>
                {content.status === ContentStatus.DRAFT ? (
                    <Button
                        variant='primary'
                        onClick={handlePublicationRequest}
                    >
                        {'Richiesta di pubblicazione'}
                    </Button>
                ) : null}
            </CardFooter>
        </Card>
    )
}

export default ContentCard