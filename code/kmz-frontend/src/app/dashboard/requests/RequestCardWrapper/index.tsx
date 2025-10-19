'use client'
import React from 'react';

import RequestStatusBadge from '@/components/RequestStatus';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';

import { APP_LABELS } from '@/settings/app-labels';
import {
    ContentPublicationResponseDto, RequestStatus, UserRegistrationResponseDto
} from '@/types/api/request/types';

interface RequestCardWrapperProps {
    request: UserRegistrationResponseDto | ContentPublicationResponseDto;
    canProcess: boolean;
    children: React.ReactNode;
    onApproveRequest?: () => void;
    onRejectRequest?: () => void;
}

const RequestCardWrapper = ({ request, canProcess, children, onApproveRequest, onRejectRequest }: RequestCardWrapperProps) => {
    return (
        <Card className='basis-[calc(50%-1rem)] h-full' >
            <CardContent>
                <div className='flex gap-3 justify-between'>
                    <RequestStatusBadge status={request.status} />
                    {canProcess && request.status === RequestStatus.PENDING ? (
                        <div className='flex gap-2'>
                            <Button
                                variant="outline_primary"
                                onClick={onApproveRequest}
                            >
                                {APP_LABELS.APPROVE}
                            </Button>
                            <Button
                                variant="destructive"
                                onClick={onRejectRequest}
                            >
                                {APP_LABELS.REJECT}
                            </Button>
                        </div>
                    ) : null}
                </div>
            </CardContent>
            <CardContent>
                <Card>
                    <CardContent>
                        {children}
                    </CardContent>
                </Card>
            </CardContent>
        </Card>
    )
}

export default RequestCardWrapper