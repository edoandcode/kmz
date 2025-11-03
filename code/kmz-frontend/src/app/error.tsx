'use client'
import React from 'react';

import Grid from '@/components/Grid';
import PageWrapper from '@/components/PageWrapper';

const ErrorPage = () => {

    return (
        <PageWrapper className="flex flex-col justify-center items-center bg-negative text-positive">
            <Grid>
                <Grid.Col
                    span={12}
                    span-lg={8}
                    offset-lg={2}
                >
                    <div className='flex flex-col w-full justify-center items-center gap-5'>

                        <h1 className='text-2xl font-medium'>Error</h1>
                        <p className='text-lg'>Something went wrong. Please try to refresh the page.</p>
                    </div>
                </Grid.Col>
            </Grid>
        </PageWrapper>
    )
}

export default ErrorPage