import { redirect } from 'next/navigation';

import { ROUTES } from '@/settings/routes';

const DashboardPage = () => {

    redirect(`/${ROUTES.DASHBOARD_CONTENTS}`);
}

export default DashboardPage