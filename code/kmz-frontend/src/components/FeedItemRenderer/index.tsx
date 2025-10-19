import React from 'react';

import { Card, CardContent } from '@/components/ui/card';

import { FeedItem } from '@/types/api/feed/types';

import CardContentRenderer from '../CardContentRenderer';

const FeedItemRenderer = ({ item }: { item: FeedItem }) => {
    return (
        <Card>
            <CardContent>
                <p>Published at: {new Date(item.publishedAt).toLocaleDateString()}</p>
                <CardContentRenderer
                    content={item.content}
                ></CardContentRenderer>
            </CardContent>
        </Card>
    )
}

export default FeedItemRenderer