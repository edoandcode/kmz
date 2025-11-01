/// USER
export enum UserRole {
    ADMINISTRATOR = 'ADMINISTRATOR',
    CURATOR = 'CURATOR',
    FACILITATOR = 'FACILITATOR',
    GENERIC_USER = 'GENERIC_USER',
    PROCESSOR = 'PROCESSOR',
    PRODUCER = 'PRODUCER'
}

export interface RegisterUserDto {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    roles?: UserRole[];
}


export interface UserDto {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    roles?: UserRole[];
}
