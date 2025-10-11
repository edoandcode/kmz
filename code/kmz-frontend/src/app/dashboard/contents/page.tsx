import React from 'react';

import { clsx } from 'clsx';

import { auth } from '@/services/next-auth';

import ContentsList from './ContentsList';
import CreateContent from './CreateContent';

const DashboardMyContents = async () => {
    const session = await auth();

    console.log('session dashboard', session);

    return (
        <div className={clsx(
            "flex flex-col gap-6"
        )}>
            <CreateContent session={session}></CreateContent>
            <ContentsList session={session}></ContentsList>
        </div>
    )
}

export default DashboardMyContents