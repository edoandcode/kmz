import React from 'react';

import ContentPublications from './ContentPublications';

import type { Session } from 'next-auth';

const RequestList = ({ session }: { session: Session | null }) => {
    return (
        <div>
            <h2 className="text-2xl font-medium mb-4">Richieste di pubblicazione inviate</h2>
            <ContentPublications session={session}></ContentPublications>
        </div>
    )
}

export default RequestList