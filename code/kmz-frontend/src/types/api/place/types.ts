export type Location = {
    latitude: number;
    longitude: number;
}


export interface Place {
    name: string;
    description?: string;
    location: Location;
}