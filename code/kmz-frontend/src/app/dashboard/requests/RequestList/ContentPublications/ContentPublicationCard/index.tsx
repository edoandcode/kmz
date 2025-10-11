import React from 'react';

import { VariantProps } from 'class-variance-authority';

import KeyValueItem from '@/components/KeyValueItem';
import RequestStatusBadge from '@/components/RequestStatus';
import { Badge } from '@/components/ui/badge';
import { Card, CardContent } from '@/components/ui/card';

import { ContentPublicationResponseDto, RequestType } from '@/types/api/request/types';

const contentTypeMap: Partial<Record<RequestType, { label: string }>> = {
    CONTENT_PUBLICATION: { label: 'CONTENT PUBLICATION' },
}

const ContentPublicationCard = ({ request }: { request: ContentPublicationResponseDto }) => {
    return (
        <Card>
            <CardContent>
                <div className='flex gap-3'>
                    <RequestStatusBadge status={request.status} />
                    <Badge variant="outline">{contentTypeMap[request.type]?.label}</Badge>
                </div>
                <div className="flex flex-col gap-1 mt-2">
                    <KeyValueItem label={'requester ID'} value={request.requesterId} />
                </div>
            </CardContent>
        </Card>
    )
}

export default ContentPublicationCard