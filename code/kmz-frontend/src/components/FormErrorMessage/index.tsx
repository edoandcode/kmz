import React from 'react';

import clsx from 'clsx';

import type { PropsWithChildren } from 'react';

const FormErrorMessage = ({ children }: PropsWithChildren) => {
    return (
        <div className={clsx(
            'text-xs text-red-600',
            'absolute -bottom-4 left-0',
        )}>{children}</div>
    )
}

export default FormErrorMessage