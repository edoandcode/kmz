import React from 'react';

import { Card, CardContent } from '@/components/ui/card';

import { FeedItem } from '@/types/api/feed/types';

import CardContentRenderer from '../CardContentRenderer';

const FeedItemRenderer = ({ item }: { item: FeedItem }) => {
    return (
        <Card>
            <CardContent className="flex flex-col gap-2">
                <small>Published at: {new Date(item.publishedAt).toLocaleDateString()}</small>
                <CardContentRenderer
                    content={item.content}
                ></CardContentRenderer>
            </CardContent>
        </Card>
    )
}

export default FeedItemRenderer