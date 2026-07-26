import { useEffect, useState } from "react";
import axios from "axios";

function DeliveriesPage() {

    const [deliveries, setDeliveries] =
        useState([]);

    const [search, setSearch] =
        useState("");

    useEffect(() => {

        loadDeliveries();

    }, []);

    const loadDeliveries = async () => {

        try {

            const response =
                await axios.get(
                    "http://localhost:8080/deliveries"
                );

            setDeliveries(
                response.data
            );

        } catch (error) {

            console.error(error);

            alert(
                "Failed To Load Deliveries"
            );
        }
    };

    const updateStatus = async (
        id
    ) => {

        const status =
            prompt(
                "Enter Status (ASSIGNED, PICKED_UP, DELIVERED)"
            );

        if (!status) {

            return;
        }

        try {

            await axios.put(
                `http://localhost:8080/deliveries/${id}?status=${status}`
            );

            alert(
                "Status Updated Successfully"
            );

            loadDeliveries();

        } catch (error) {

            console.error(error);

            alert(
                "Failed To Update Status"
            );
        }
    };

    const filteredDeliveries =
        deliveries.filter(
            (delivery) =>
                delivery.orderId
                    ?.toString()
                    .includes(search)
        );

    return (

        <div>

            <h1>
                Delivery Management
            </h1>

            <hr />

            <input
                type="text"
                placeholder="Search By Order Id"
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

                        <th>Order ID</th>

                        <th>Delivery Agent</th>

                        <th>Status</th>

                        <th>Created At</th>

                        <th>Actions</th>

                    </tr>

                </thead>

                <tbody>

                    {filteredDeliveries.map(
                        (delivery) => (

                            <tr
                                key={delivery.id}
                            >

                                <td>
                                    {delivery.id}
                                </td>

                                <td>
                                    {delivery.orderId}
                                </td>

                                <td>
                                    {delivery.deliveryAgent}
                                </td>

                                <td>
                                    {delivery.status}
                                </td>

                                <td>
                                    {delivery.createdAt}
                                </td>

                                <td>

                                    <button
                                        onClick={() =>
                                            updateStatus(
                                                delivery.id
                                            )
                                        }
                                    >
                                        Update Status
                                    </button>

                                </td>

                            </tr>

                        )
                    )}

                </tbody>

            </table>

        </div>
    );
}

export default DeliveriesPage;