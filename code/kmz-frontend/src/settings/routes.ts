const SITE_URL = process.env.NEXT_PUBLIC_SITE_URL
const BASE_PATH = process.env.NEXT_PUBLIC_BASE_PATH || ""

const ROUTES = {
    SIGNUP_SUPERADMIN: 'signup/super-admin',
    SIGNUP: 'signup',
    LOGIN: 'login',
    DASHBOARD: 'dashboard',
}


export {
    ROUTES,
    SITE_URL,
    BASE_PATH,
}