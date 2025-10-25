import { UserDto } from '../user/types';

// CONTENT
export enum ContentType {
    PRODUCT = 'PRODUCT',
    PROCESS = 'PROCESS',
    EVENT = 'EVENT',
    PROCESSED_PRODUCT = 'PROCESSED_PRODUCT'
}


export enum ContentStatus {
    DRAFT = 'DRAFT',
    PENDING = 'PENDING',
    PUBLISHED = 'PUBLISHED',
    REJECTED = 'REJECTED'
}

export type Content = {
    id: number;
    type: ContentType;
    authorId: number;
    status: ContentStatus;
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

export interface ProductContentDto {
    id?: number;
    authorId: number;
    type: ContentType.PRODUCT;
    status: ContentStatus;
    name: string;
    description?: string;
    sowingDate: Date;
    harvestDate: Date;
    cultivationMethod?: string;
    certifications?: string[];
}


// PROCESS

export interface ProcessContent extends Content {
    name: string;
    description?: string;
    certifications?: string[];
}

export interface ProcessContentDto {
    id?: number;
    authorId: number;
    type: ContentType.PROCESS;
    status: ContentStatus;
    name: string;
    description?: string;
    certifications?: string[];
}


// EVENTS

export interface EventContent extends Content {
    name: string;
    date: Date;
    description: string;
    location: string;
    guests?: UserDto[]
}


// PROCESSED PRODUCT

export interface ProcessedProductContent extends Content {
    name: string;
    description?: string;
    ingredients: ProductContentDto[];
    processes: ProcessContentDto[];
    processingDate: Date;
    certifications?: string[];
}