import React from 'react';

import KeyValueItem from '@/components/KeyValueItem';
import RequestStatusBadge from '@/components/RequestStatus';
import { Badge } from '@/components/ui/badge';
import { Card, CardContent } from '@/components/ui/card';

import { APP_LABELS } from '@/settings/app-labels';
import { UserRegistrationResponseDto } from '@/types/api/request/types';

const UserRegistrationCard = ({ request }: { request: UserRegistrationResponseDto }) => {
    return (
        <Card className='basis-[calc(50%-1rem)] h-full' >
            <CardContent>
                <div className='flex gap-3'>
                    <RequestStatusBadge status={request.status} />
                    <Badge variant="outline">{APP_LABELS.USER_REGISTRATION}</Badge>
                </div>
                <div className="flex flex-col gap-1 mt-2">
                </div>
            </CardContent>
            <CardContent>
                <Card>
                    <CardContent>
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
                    </CardContent>
                </Card>
            </CardContent>
        </Card>
    )
}

export default UserRegistrationCard