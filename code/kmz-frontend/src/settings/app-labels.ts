type AppLabels = {
    readonly [key: string]: string
}


const APP_LABELS: AppLabels = {
    DASHBOARD: 'Dashboard',
    LOGIN: 'Login',
    LOGOUT: 'Logout',
    HOME: 'Home',
    MY_REQUESTS: 'Le mie richieste',
    MY_CONTENTS: 'I miei contenuti',
    APPROVE: 'Approva',
    REJECT: 'Rifiuta',
    CONTENTS_PUBLICATION_REQUESTS: 'Richieste di pubblicazione contenuti',
    CONTENTS_PUBLICATION_REQUESTS_SENT: 'Richieste di pubblicazione inviate',
    USER_REGISTRATION_REQUESTS: 'Richieste di registrazione utente',
    USER_REGISTRATION_REQUESTS_SENT: 'Richieste di registrazione inviate',
}

export { APP_LABELS };