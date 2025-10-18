import _ from 'lodash';

import { fetchApi } from './http';

import type { FetchApiOptions } from './http';

/**
 * Performs a GET request to the specified API endpoint.
 *
 * @param endpoint - The API endpoint to fetch data from.
 * @param options - Optional fetch options.
 * @returns The response data from the API or an error.
 */
export const get = async <T>(endpoint: string, options?: FetchApiOptions): Promise<T> => {
    console.log(`GET ${endpoint}`, options);
    return fetchApi<T>(endpoint, {
        method: 'GET',
        ...options,
    });
}


/** * Performs a POST request to the specified API endpoint with the provided body.
 *
 * @param endpoint - The API endpoint to send data to.
 * @param body - The body of the POST request, containing the data to be sent.
 * @param options - Optional fetch options.
 * @returns The response data from the API or an error.
 */
export const post = async <T>(endpoint: string, body: unknown, options?: FetchApiOptions): Promise<T> => {
    return fetchApi<T>(endpoint, {
        method: 'POST',
        body: JSON.stringify(body),
        headers: {
            'Content-Type': 'application/json',
            ...options?.headers,
        },
        ..._.omit(options, ['headers']),
    });
}

