import axios from "axios";

const API_URL =
    "http://localhost:8080";

export const getInventory =
    async () => {

        const response =
            await axios.get(
                `${API_URL}/inventory`
            );

        return response.data;
    };

export const updateInventory =
    async (
        itemName,
        quantity
    ) => {

        const response =
            await axios.put(
                `${API_URL}/inventory/update?itemName=${itemName}&quantity=${quantity}`
            );

        return response.data;
    };

export const reduceStock =
    async (
        itemName,
        quantity
    ) => {

        const response =
            await axios.put(
                `${API_URL}/inventory?itemName=${itemName}&quantity=${quantity}`
            );

        return response.data;
    };