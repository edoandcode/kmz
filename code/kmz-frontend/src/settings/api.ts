
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
    MY_PRODUCTS: 'products/me',
    /*EVENTS */
    EVENTS: 'events',
    MY_EVENTS: 'events/me',
    /*PROCESS */
    PROCESS: 'processes',
    MY_PROCESSES: 'processes/me',
    /*REQUESTS CONTENTS */
    REQUESTS_CONTENTS_PUBLICATION: 'requests/contents/publication',
    REQUEST_CONTENTS_PUBLICATION_MY: 'requests/contents/publication/me',
    REQUEST_CONTENTS_PUBLISH_CONTENT: 'requests/contents/publish',
    /*REQUESTS USER REGISTRATION*/
    REQUESTS_USER_REGISTRATION: 'requests/users/registration',
    REQUESTS_USER_REGISTRATION_MY: 'requests/users/registration/me',
    REQUESTS_USER_APPROVE: 'requests/users/approve',
    REQUESTS_USER_REJECT: 'requests/users/reject',
}


export {
    API,
}