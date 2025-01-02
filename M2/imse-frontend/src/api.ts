
const BASE_URL = 'http://localhost:8081';

export const fetchData = async <T>(endpoint: string): Promise<T | null> => {
    try {
        const response = await fetch(`${BASE_URL}${endpoint}`);
        if (!response.ok) {
            throw new Error(`Error: ${response.statusText}`);
        }
        return await response.json();
    } catch (error) {
        console.error(error);
        return null;
    }
};