import { Content } from '@/types/api/content/types';

export enum RequestStatus {
    PENDING = 'PENDING',
    APPROVED = 'APPROVED',
    REJECTED = 'REJECTED'
}


export enum RequestType {
    CONTENT_PUBLICATION = 'CONTENT_PUBLICATION',
    USER_REGISTRATION = 'USER_REGISTRATION',
    EVENT_PARTICIPATION = 'EVENT_PARTICIPATION',
}


export interface ContentPublicationResponseDto {
    id: number;
    content: Content;
    status: RequestStatus;
    createdAt: Date;
    processedAt: Date;
    message: string;
    recipientId: number;
    requesterId: number;
    type: RequestType;
}