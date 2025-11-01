import React from 'react';

import Grid from '@/components/Grid';
import PageWrapper from '@/components/PageWrapper';
import { Skeleton } from '@/components/ui/skeleton';

const DashboardLayoutSkeleton = () => {
    return (
        <PageWrapper className="opacity-20">
            <Grid>
                <Grid.Col
                    span={4}
                >
                    <Skeleton className="h-[23px] w-[180px] rounded-sm mb-3"></Skeleton>
                    <Skeleton className="h-[23px] w-[180px] rounded-sm"></Skeleton>
                </Grid.Col>
                <Grid.Col
                    span={8}
                >
                    <Skeleton className="h-[200px] w-full rounded-sm"></Skeleton>
                </Grid.Col>
            </Grid>
        </PageWrapper>
    )
}

export default DashboardLayoutSkeleton