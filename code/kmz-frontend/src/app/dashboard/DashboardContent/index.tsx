'use client'
import React, { PropsWithChildren } from 'react';

import { useSession } from 'next-auth/react';

import Grid from '@/components/Grid';
import PageWrapper from '@/components/PageWrapper';

import DashboardNav from './DashboardNav';

const DashboardContent = ({ children }: PropsWithChildren) => {
    const { status, data: session } = useSession();

    if (status === 'loading')
        return <div>Loading...</div>

    return (
        <PageWrapper>
            <Grid>
                <Grid.Col
                    span={4}
                >
                    <DashboardNav></DashboardNav>
                </Grid.Col>
                <Grid.Col
                    span={8}
                >
                    {children}
                </Grid.Col>
            </Grid>
        </PageWrapper>
    )
}

export default DashboardContent