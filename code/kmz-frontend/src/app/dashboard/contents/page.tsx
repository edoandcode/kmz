import React from 'react';

import { clsx } from 'clsx';

import ContentsList from './ContentsList';
import CreateContent from './CreateContent';

const DashboardPage = async () => {

    return (
        <div className={clsx(
            "flex flex-col gap-6"
        )}>
            <CreateContent ></CreateContent>
            <ContentsList ></ContentsList>
        </div>
    )
}

export default DashboardPage