import type { Metadata } from "next";
import { getFeed } from "@/services/api";
import PageWrapper from "@/components/PageWrapper";
import { notFound } from "next/navigation";


export const metadata: Metadata = {
    title: "KMZ - Home Page",
    description: "This is the home page description",
}

export default async function Home() {

    const feedData = await getFeed();

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
