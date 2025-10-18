import { Content } from '@/types/api/content/types';

type FeedItem = {
    id: number;
    content: Content,
    publishedAt: string;
}

export type { FeedItem }