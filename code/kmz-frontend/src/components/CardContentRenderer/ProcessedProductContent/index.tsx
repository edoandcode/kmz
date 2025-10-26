import React from 'react';

import KeyValueItem from '@/components/KeyValueItem';
import { CardDescription, CardTitle } from '@/components/ui/card';

import {
    Content, ProcessedProductContent as ProcessedProductContentType
} from '@/types/api/content/types';

import CardLayout from '../CardLayout';

const ProcessedProductContent = ({ content }: { content: Content }) => {

  const processedProduct = content as ProcessedProductContentType;

  return (
    <CardLayout
      content={() => {
        return (
          <>
            <div className="flex flex-col gap-2 mb-3">
              <CardTitle>{processedProduct.name}</CardTitle>
              <CardDescription>{processedProduct.description}</CardDescription>
            </div>
            <KeyValueItem label="ingredienti" value={processedProduct.ingredients.map(i => i.name).join(", ")} />
            <KeyValueItem label="processi" value={processedProduct.processes.map(p => p.name).join(", ")} />

          </>
        )
      }}
    ></CardLayout>

  )
}

export default ProcessedProductContent