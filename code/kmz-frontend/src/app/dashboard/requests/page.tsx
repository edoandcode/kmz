import React from 'react';

import { auth } from '@/services/next-auth';

import RequestList from './RequestList';

const DashboardMyRequestsPage = async () => {
    const session = await auth();

    return (
        <div>
            <RequestList session={session}></RequestList>
        </div>
    )
}

export default DashboardMyRequestsPage