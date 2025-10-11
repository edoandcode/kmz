import React, { PropsWithChildren } from 'react';

import DashboardLayoutContent from './DashboardLayout';

const DashboardLayout = ({ children }: PropsWithChildren) => {
    return (
        <div>
            <DashboardLayoutContent>
                {children}
            </DashboardLayoutContent>
        </div>
    )
}

export default DashboardLayout