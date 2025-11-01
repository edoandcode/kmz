import React, { ReactNode } from 'react'
import { SessionProvider } from 'next-auth/react'

const AuthenticationProvider = ({ children }: { children: ReactNode }) => {
    return (
        <SessionProvider>
            {children}
        </SessionProvider>
    )
}

export default AuthenticationProvider