// CONTENT
export enum ContentType {
    PRODUCT = 'PRODUCT',
    PROCESS = 'PROCESS',
    EVENT = 'EVENT'
}


export type Content = {
    id: number;
    type: ContentType;
    authorId: number;
}


// PRODUCT

export interface ProductContent extends Content {
    name: string;
    description?: string;
    sowingDate: Date;
    harvestDate: Date;
    cultivationMethod?: string;
    certifications?: string[];
}