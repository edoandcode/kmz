import React, { PropsWithChildren } from 'react';

import DashboardContent from './DashboardContent';

const DashboardLayout = ({ children }: PropsWithChildren) => {
    return (
        <div>
            <DashboardContent>
                {children}
            </DashboardContent>
        </div>
    )
}

export default DashboardLayout