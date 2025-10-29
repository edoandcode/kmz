import type { Metadata } from "next";
import { notFound } from 'next/navigation';

import FeedItemRenderer from '@/components/FeedItemRenderer';
import Grid from '@/components/Grid';
import PageWrapper from '@/components/PageWrapper';

import { get } from '@/services/api';
import { API } from '@/settings/api';
import { APP_LABELS } from '@/settings/app-labels';
import {
    ContentType, EventContent, ProcessedProductContent, ProductContent
} from '@/types/api/content/types';
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
    const processedProducts = feedData.filter((item: FeedItem) => item.content.type === ContentType.PROCESSED_PRODUCT).map(item => ({ ...item, content: item.content as ProcessedProductContent }));
    const processes = feedData.filter((item: FeedItem) => item.content.type === ContentType.PROCESS).map(item => ({ ...item, content: item.content as ProcessedProductContent }));


    return (
        <PageWrapper className="flex flex-col gap-8">
            <Grid className="gap-y-[var(--gap)]">
                <Grid.Col span={12}>
                    <h2 className="typography-title">{APP_LABELS.PRODUCTS}</h2>
                </Grid.Col>
                {products?.length ? (
                    <>

                        {products.map((productItem) => {
                            return (
                                <Grid.Col
                                    key={productItem.id}
                                    span={12}
                                    span-xl={6}
                                >
                                    <FeedItemRenderer item={productItem}></FeedItemRenderer>
                                </Grid.Col>
                            )
                        })}
                    </>
                ) : <p>No products found</p>}
            </Grid>
            <Grid className="gap-y-[var(--gap)]">
                <Grid.Col span={12}>
                    <h2 className="typography-title">{APP_LABELS.EVENTS}</h2>
                </Grid.Col>
                {events?.length ? (
                    <>
                        {events.map((eventItem) => {
                            return (
                                <Grid.Col
                                    key={eventItem.id}
                                    span={12}
                                    span-xl={6}
                                >
                                    <FeedItemRenderer item={eventItem}></FeedItemRenderer>
                                </Grid.Col>
                            )
                        })}
                    </>
                ) : <p>No events found</p>}
            </Grid>
            <Grid className="gap-y-[var(--gap)]">
                <Grid.Col span={12}>
                    <h2 className="typography-title">{APP_LABELS.PROCESSED_PRODUCTS}</h2>
                </Grid.Col>
                {processedProducts?.length ? (
                    <>
                        {processedProducts.map((processedProductItem) => {
                            return (
                                <Grid.Col
                                    key={processedProductItem.id}
                                    span={12}
                                    span-xl={6}
                                >
                                    <FeedItemRenderer item={processedProductItem}></FeedItemRenderer>
                                </Grid.Col>
                            )
                        })}
                    </>
                ) : <p>No processed products found</p>}
            </Grid>
            <Grid className="gap-y-[var(--gap)]">
                <Grid.Col span={12}>
                    <h2 className="typography-title">{APP_LABELS.PROCESSES}</h2>
                </Grid.Col>
                {processes?.length ? (
                    <>
                        {processes.map((processItem) => {
                            return (
                                <Grid.Col
                                    key={processItem.id}
                                    span={12}
                                    span-xl={6}
                                >
                                    <FeedItemRenderer item={processItem}></FeedItemRenderer>
                                </Grid.Col>
                            )
                        })}
                    </>
                ) : <p>No processes found</p>}
            </Grid>
        </PageWrapper >
    );
}
