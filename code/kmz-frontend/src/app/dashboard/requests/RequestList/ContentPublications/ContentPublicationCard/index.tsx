import React from 'react';

import CardContentRenderer from '@/components/CardContentRenderer';
import KeyValueItem from '@/components/KeyValueItem';
import RequestStatusBadge from '@/components/RequestStatus';
import { Badge } from '@/components/ui/badge';
import { Card, CardContent } from '@/components/ui/card';

import { ContentType } from '@/types/api/content/types';
import { ContentPublicationResponseDto, RequestType } from '@/types/api/request/types';

/* const contentTypeMap: Partial<Record<RequestType, { label: string }>> = {
    CONTENT_PUBLICATION: { label: 'CONTENT PUBLICATION' },
} */

const contentTypeMap: Partial<Record<ContentType, { label: string }>> = {
    PRODUCT: { label: 'PRODOTTO' },
    PROCESS: { label: 'PROCESSO' },
    EVENT: { label: 'EVENTO' },
}

const ContentPublicationCard = ({ request }: { request: ContentPublicationResponseDto }) => {
    return (
        <Card className='basis-[calc(50%-1rem)] h-full' >
            <CardContent>
                <div className='flex gap-3'>
                    <RequestStatusBadge status={request.status} />
                    <Badge variant="outline">{contentTypeMap[request.content.type]?.label}</Badge>
                </div>
                <div className="flex flex-col gap-1 mt-2">
                </div>
            </CardContent>
            <CardContent>
                <Card className="bg-background">
                    <CardContentRenderer content={request.content} />
                </Card>
            </CardContent>
        </Card>
    )
}

export default ContentPublicationCard