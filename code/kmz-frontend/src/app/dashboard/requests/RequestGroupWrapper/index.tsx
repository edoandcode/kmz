import React, { PropsWithChildren } from 'react';

import clsx from 'clsx';

const RequestGroupWrapper = ({ children }: PropsWithChildren) => {
    return (
        <div className={clsx(
            "flex gap-4 w-full flex-col flex-grow-0",
            "xl:flex-row xl:flex-wrap"
        )}>
            {children}
        </div>
    )
}

export default RequestGroupWrapper