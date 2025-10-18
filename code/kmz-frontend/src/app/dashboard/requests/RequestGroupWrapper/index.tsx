import React, { PropsWithChildren } from 'react';

const RequestGroupWrapper = ({ children }: PropsWithChildren) => {
    return (
        <div className='flex gap-4 flex-wrap align-stretch justify-items-stretch'>
            {children}
        </div>
    )
}

export default RequestGroupWrapper