import React from 'react'
import clsx from "clsx"
import LoginForm from "./LoginForm"

const LoginPage = () => {
    return (
        <div className={clsx(
            "w-svw h-svh flex items-center justify-center",
            "bg-neutral-100"
        )}>
            <LoginForm />
        </div>
    )
}

export default LoginPage