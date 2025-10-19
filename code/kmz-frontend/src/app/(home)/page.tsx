import type { Metadata } from "next";
import { ReactElement } from 'react';

import { ColProps } from '@edoandcode/ui-kit-grid';
import { notFound } from 'next/navigation';

import FeedItemRenderer from '@/components/FeedItemRenderer';
import Grid from '@/components/Grid';
import PageWrapper from '@/components/PageWrapper';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { APP_LABELS } from '@/settings/app-labels';
import { ContentType, EventContent, ProductContent } from '@/types/api/content/types';
import { FeedItem } from '@/types/api/feed/types';

export const metadata: Metadata = {
    title: "KMZ - Home Page",
    description: "This is the home page description",
}

export default async function Home() {

    let feedData: FeedItem[];

    try {
        feedData = await get<FeedItem[]>(`${API.FEED}`);
    } catch (error) {
        console.error("Error fetching feed data:", error);
        notFound();
    }


    //const processes = feedData.filter((item: FeedItem) => item.content.type === ContentType.PROCESS);
    const products = feedData.filter((item: FeedItem) => item.content.type === ContentType.PRODUCT).map(item => ({ ...item, content: item.content as ProductContent }));
    const events = feedData.filter((item: FeedItem) => item.content.type === ContentType.EVENT).map(item => ({ ...item, content: item.content as EventContent }));


    return (
        <PageWrapper className="flex flex-col gap-8">
            {products?.length ? (
                <Grid>
                    <Grid.Col span={12}>
                        <h2 className="text-4xl mb-4">{APP_LABELS.PRODUCTS}</h2>
                    </Grid.Col>
                    {products.map((productItem) => {
                        return (
                            <Grid.Col
                                key={productItem.id}
                                span={12}
                                span-md={6}
                            >
                                <FeedItemRenderer item={productItem}></FeedItemRenderer>
                            </Grid.Col>
                        )
                    })}
                </Grid>
            ) : null}
            {events?.length ? (
                <Grid>
                    <Grid.Col span={12}>
                        <h2 className="text-4xl mb-4">{APP_LABELS.EVENTS}</h2>
                    </Grid.Col>
                    {events.map((eventItem) => {
                        return (
                            <Grid.Col
                                key={eventItem.id}
                                span={12}
                                span-md={6}
                            >
                                <FeedItemRenderer item={eventItem}></FeedItemRenderer>
                            </Grid.Col>
                        )
                    })}
                </Grid>
            ) : null}
        </PageWrapper >
    );
}
