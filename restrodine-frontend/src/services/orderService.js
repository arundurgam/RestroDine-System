import axios from "axios";

const API_URL = "http://localhost:8080";

export const placeOrder = async (order) => {

    const token = localStorage.getItem("token");

    const response = await axios.post(
        `${API_URL}/orders`,
        order,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );

    return response.data;
};