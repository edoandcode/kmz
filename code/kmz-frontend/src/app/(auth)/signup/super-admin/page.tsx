import React from 'react';

import clsx from 'clsx';

import SignupForm from '../SignupForm';

const SignupPage = () => {
    return (
        <div className={clsx(
            "w-svw h-svh flex items-center justify-center",
            "bg-neutral-100 dark:bg-neutral-900"

        )}>
            <SignupForm isSuperAdminSetup={true} />
        </div>
    )
}

export default SignupPage