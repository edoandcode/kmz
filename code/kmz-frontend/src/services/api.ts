
import { API } from "@/settings/api";

export const getFeed = async () => {
    const response = await fetch(`${API.BASE_URL}/${API.FEED}`);

    if (!response.ok) {
        return null
    }
    const data = await response.json();
    return data;
}




export const getSystemStatus = async () => {
    const response = await fetch(`${API.BASE_URL}/${API.SYSTEM_STATUS}`);

    if (!response.ok) {
        return null
    }
    const data = await response.json();
    return data;
}