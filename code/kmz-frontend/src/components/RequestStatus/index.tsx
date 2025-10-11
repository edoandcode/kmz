import React from 'react';

import { VariantProps } from 'class-variance-authority';

import { Badge } from '@/components/ui/badge';

import { RequestStatus } from '@/types/api/request/types';

const badgeVariantMap: Record<RequestStatus, VariantProps<typeof Badge>['variant']> = {
    PENDING: 'outline',
    APPROVED: 'secondary',
    REJECTED: 'destructive',
}

const RequestStatusBadge = ({ status }: { status: RequestStatus }) => {
    return (
        <Badge variant={badgeVariantMap[status]}>
            {status}
        </Badge>
    )
}

export default RequestStatusBadge