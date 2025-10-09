import React from 'react';

import { useSession } from 'next-auth/react';

import Grid from '@/components/Grid';

import DashboardNav from './DashboardNav';

const DashboardContent = () => {
    const { status, data: session } = useSession();

    if (status === 'loading')
        return <div>Loading...</div>

    return (
        <div>
            <Grid>
                <Grid.Col
                    span={4}
                >
                    <DashboardNav></DashboardNav>
                </Grid.Col>
            </Grid>
        </div>
    )
}

export default DashboardContent