import { API } from '@/settings/api'


type FetchApiOptions = RequestInit

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


    const res = await fetch(url.href, {
        ...options,
        headers: {
            ...options.headers,
        },
    })

    if (!res.ok) {
        throw new Error(`Fetch error: ${res.status} ${res.statusText}`)
    }

    const data = await res.json()
    return data as T
}
