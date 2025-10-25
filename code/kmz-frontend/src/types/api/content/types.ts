import { UserDto } from '../user/types';

// CONTENT
export enum ContentType {
    PRODUCT = 'PRODUCT',
    PROCESS = 'PROCESS',
    EVENT = 'EVENT'
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


// PROCESS

export interface ProcessContent extends Content {
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