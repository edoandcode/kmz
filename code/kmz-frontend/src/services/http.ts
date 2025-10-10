import { API } from '@/settings/api';

export type FetchApiOptions = RequestInit

/**
 * Fetches data from the API.
 *
 * @async
 * @function fetchApi
 * @param path - The endpoint path to fetch data from. If it starts with a '/', it will be removed.
 * @param options - The fetch options, including method and headers.
 * @returns The data returned from the API.
 * @throws If the fetch request fails.
 */
export async function fetchApi<T = unknown>(
    path: string,
    options: FetchApiOptions = { method: 'GET' },
): Promise<T> {
    path = path.startsWith('/') ? path.slice(1) : path

    const url = new URL(`${API.BASE_URL}/${path}`)


    try {

        const res = await fetch(url.href, {
            ...options,
            headers: {
                ...options.headers,
            },
        })

        if (!res.ok) {
            let errorMsg = "Unexpected error";

            try {
                const errorBody = await res.json();
                errorMsg = errorBody.message || JSON.stringify(errorBody);
            } catch {
                // fallback se non è JSON
                errorMsg = `HTTP ${res.status}`;
            }

            throw new Error(errorMsg);
        }

        try {
            const data = await res.json()
            return data as T
        } catch {
            throw new Error("Invalid JSON response");
        }
    } catch (err) {
        if (err instanceof Error) throw err;
        throw new Error("Network error or invalid response");
    }



}