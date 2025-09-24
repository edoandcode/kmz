const SITE_URL = process.env.NEXT_PUBLIC_SITE_URL
const BASE_PATH = process.env.NEXT_PUBLIC_BASE_PATH || ""


const API = {
    /*BASE */
    ENDPOINT: process.env.NEXT_PUBLIC_API_ENDPOINT,
    BASE_URL: `${process.env.NEXT_PUBLIC_API_ENDPOINT}`,

}


export {
    SITE_URL,
    BASE_PATH,
    API,
}