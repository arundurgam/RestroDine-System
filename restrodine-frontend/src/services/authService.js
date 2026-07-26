import axios from "axios";

const API_URL = "http://localhost:8080";

export const register = async (user) => {

    const response = await axios.post(
        `${API_URL}/auth/register`,
        user
    );

    return response.data;
};

export const login = async (username, password) => {

    const response = await axios.post(
        `${API_URL}/auth/login?username=${username}&password=${password}`
    );

    return response.data;
};