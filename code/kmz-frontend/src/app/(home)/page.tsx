import type { Metadata } from "next";
import { notFound } from 'next/navigation';

import PageWrapper from '@/components/PageWrapper';

import { get } from '@/services/api';
import { API } from '@/settings/api';

export const metadata: Metadata = {
    title: "KMZ - Home Page",
    description: "This is the home page description",
}

export default async function Home() {

    const feedData = await get(`${API.FEED}`);

    if (!feedData)
        return notFound();


    return (
        <div className="flex items-center justify-center min-h-screen">
            <PageWrapper>
                <h1>HOMEPAGE</h1>
            </PageWrapper>
        </div>
    );
}
