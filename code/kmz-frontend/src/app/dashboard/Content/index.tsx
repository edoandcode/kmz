import React from 'react';

import { useSession } from 'next-auth/react';

import Grid from '@/components/Grid';

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
                    <h1 className="text-2xl font-bold">Dashboard</h1>
                    <p>{session?.user?.roles?.join(', ') || 'No roles assigned'}</p>
                </Grid.Col>
            </Grid>
        </div>
    )
}

export default DashboardContent