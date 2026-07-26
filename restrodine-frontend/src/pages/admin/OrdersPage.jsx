import { useEffect, useState } from "react";
import axios from "axios";

function OrdersPage() {

    const [orders, setOrders] =
        useState([]);

    const [search, setSearch] =
        useState("");

    useEffect(() => {

        loadOrders();

    }, []);

    const loadOrders = async () => {

        try {

            const response =
                await axios.get(
                    "http://localhost:8080/orders"
                );

            setOrders(
                response.data
            );

        } catch (error) {

            console.error(error);

            alert(
                "Failed To Load Orders"
            );
        }
    };

    const filteredOrders =
        orders.filter(
            (order) =>
                order.itemName
                    ?.toLowerCase()
                    .includes(
                        search.toLowerCase()
                    )
                ||
                order.id
                    ?.toString()
                    .includes(search)
        );

    return (

        <div>

            <h1>
                Orders Management
            </h1>

            <hr />

            <input
                type="text"
                placeholder="Search By Order Id Or Item Name"
                value={search}
                onChange={(e) =>
                    setSearch(
                        e.target.value
                    )
                }
            />

            <br /><br />

            <table
                border="1"
                cellPadding="10"
            >

                <thead>

                    <tr>

                        <th>ID</th>

                        <th>Item Name</th>

                        <th>Price</th>

                        <th>Restaurant ID</th>

                        <th>Status</th>

                        <th>Created At</th>

                    </tr>

                </thead>

                <tbody>

                    {filteredOrders.map(
                        (order) => (

                            <tr
                                key={order.id}
                            >

                                <td>
                                    {order.id}
                                </td>

                                <td>
                                    {order.itemName}
                                </td>

                                <td>
                                    ₹{order.price}
                                </td>

                                <td>
                                    {order.restaurantId}
                                </td>

                                <td>
                                    {order.status}
                                </td>

                                <td>
                                    {order.createdAt}
                                </td>

                            </tr>

                        )
                    )}

                </tbody>

            </table>

        </div>
    );
}

export default OrdersPage;