import React from 'react';

import { ContentType } from '@/types/api/content/types';

import EventContent from './EventContent';
import ProcessContent from './ProcessContent';
import ProcessedProductContent from './ProcessedProductContent';
import ProductContent from './ProductContent';

import type { Content, } from '@/types/api/content/types';
const contentMap: Record<ContentType, React.ElementType> = {
    [ContentType.PRODUCT]: ProductContent,
    [ContentType.PROCESS]: ProcessContent,
    [ContentType.EVENT]: EventContent,
    [ContentType.PROCESSED_PRODUCT]: ProcessedProductContent
}


const CardContentRenderer = ({ content }: { content: Content }) => {
    const ContentComponent = contentMap[content.type];
    return (
        <ContentComponent content={content} />
    )
}

export default CardContentRenderer