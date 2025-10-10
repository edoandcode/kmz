import React from 'react';

import { auth } from '@/services/next-auth';

import CreateContent from './CreateContent';

const DashboardMyContents = async () => {
    const session = await auth();

    console.log('session dashboard', session);

    return (
        <div>
            <CreateContent session={session}></CreateContent>
        </div>
    )
}

export default DashboardMyContents