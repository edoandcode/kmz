'use client'
import React, { PropsWithChildren } from 'react';

import { useSession } from 'next-auth/react';

import Grid from '@/components/Grid';
import PageWrapper from '@/components/PageWrapper';

import DashboardLayoutSkeleton from './DashboardLayoutSkeleton';
import DashboardNav from './DashboardNav';

const DashboardLayout = ({ children }: PropsWithChildren) => {
    const { status, } = useSession();

    if (status === 'loading')
        return <DashboardLayoutSkeleton></DashboardLayoutSkeleton>

    return (
        <PageWrapper>
            <Grid>
                <Grid.Col
                    span={3}
                >
                    <DashboardNav></DashboardNav>
                </Grid.Col>
                <Grid.Col
                    span={9}
                >
                    {children}
                </Grid.Col>
            </Grid>
        </PageWrapper>
    )
}

export default DashboardLayout