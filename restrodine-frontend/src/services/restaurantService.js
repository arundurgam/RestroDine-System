import axios from "axios";

const API_URL =
    "http://localhost:8080";

export const getRestaurants =
    async () => {

        const response =
            await axios.get(
                `${API_URL}/restaurants`
            );

        return response.data;
    };

export const deleteRestaurant =
    async (id) => {

        const response =
            await axios.delete(
                `${API_URL}/restaurants/${id}`
            );

        return response.data;
    };

export const updateRestaurant =
    async (
        id,
        restaurant
    ) => {

        const response =
            await axios.put(
                `${API_URL}/restaurants/${id}`,
                restaurant
            );

        return response.data;
    };