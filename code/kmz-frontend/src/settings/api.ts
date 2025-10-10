
const API = {
    /*BASE */
    ENDPOINT: process.env.NEXT_PUBLIC_API_ENDPOINT,
    BASE_URL: `${process.env.NEXT_PUBLIC_API_ENDPOINT}`,
    /*FEED */
    FEED: 'public/contents',
    /*SYSTEM STATUS*/
    SYSTEM_STATUS: 'system/status',
    /*AUTH*/
    LOGIN: 'auth/login',
    REFRESH: 'auth/refresh',
    ME: 'auth/me',
    /*USER*/
    SETUP_SUPERADMIN: 'users/setup-admin',
    SIGN_UP: 'users',
    /*PRODUCTS */
    PRODUCTS: 'products',
}


export {
    API,
}