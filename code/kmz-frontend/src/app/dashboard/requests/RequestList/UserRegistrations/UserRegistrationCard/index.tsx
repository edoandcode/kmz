import React from 'react';

import KeyValueItem from '@/components/KeyValueItem';
import RequestStatusBadge from '@/components/RequestStatus';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';

import { APP_LABELS } from '@/settings/app-labels';
import { UserRegistrationResponseDto } from '@/types/api/request/types';

import RequestCardWrapper from '../../../RequestCardWrapper';

const UserRegistrationCard = ({ request, canProcess }: { request: UserRegistrationResponseDto, canProcess: boolean }) => {
    return (
        <RequestCardWrapper
            request={request}
            canProcess={canProcess}
        >
            <KeyValueItem
                label="nome"
                value={`${request.userFirstName} ${request.userLastName}`}
            />
            <KeyValueItem
                label="createdAt"
                value={new Date(request.createdAt).toLocaleDateString()}
            />
            <KeyValueItem
                label="ruolo richiesto"
                value={request.requestedRole}
            />
        </RequestCardWrapper>

    )
}

export default UserRegistrationCard