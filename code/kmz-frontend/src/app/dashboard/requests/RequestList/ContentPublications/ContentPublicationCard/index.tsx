import React from 'react';

import CardContentRenderer from '@/components/CardContentRenderer';
import RequestStatusBadge from '@/components/RequestStatus';
import { Badge } from '@/components/ui/badge';
import { Card, CardContent } from '@/components/ui/card';

import { ContentType } from '@/types/api/content/types';
import { ContentPublicationResponseDto } from '@/types/api/request/types';

import RequestCardWrapper from '../../../RequestCardWrapper';

/* const contentTypeMap: Partial<Record<RequestType, { label: string }>> = {
    CONTENT_PUBLICATION: { label: 'CONTENT PUBLICATION' },
} */

const contentTypeMap: Partial<Record<ContentType, { label: string }>> = {
    PRODUCT: { label: 'PRODOTTO' },
    PROCESS: { label: 'PROCESSO' },
    EVENT: { label: 'EVENTO' },
}

const ContentPublicationCard = ({ request, canProcess }: { request: ContentPublicationResponseDto, canProcess: boolean }) => {
    return (
        <RequestCardWrapper
            request={request}
            canProcess={canProcess}
        >
            <CardContentRenderer content={request.content} />
        </RequestCardWrapper>
    )
}

export default ContentPublicationCard