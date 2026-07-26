import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

function InventoryPage() {

    const [inventories, setInventories] =
        useState([]);

    useEffect(() => {

        loadInventory();

    }, []);

    const loadInventory = async () => {

        try {

            const response =
                await axios.get(
                    "http://localhost:8080/inventory"
                );

            setInventories(
                response.data
            );

        } catch (error) {

            console.error(error);

            alert(
                "Failed To Load Inventory"
            );
        }
    };

    const reduceStock = async (
        itemName
    ) => {

        const quantity =
            prompt(
                "Enter Quantity To Reduce"
            );

        if (!quantity) {

            return;
        }

        try {

            await axios.put(
                `http://localhost:8080/inventory?itemName=${itemName}&quantity=${quantity}`
            );

            alert(
                "Stock Updated Successfully"
            );

            loadInventory();

        } catch (error) {

            console.error(error);

            alert(
                "Failed To Reduce Stock"
            );
        }
    };

    return (

        <div>

            <h1>
                Inventory Management
            </h1>

            <hr />

            <Link to="/add-inventory">

                <button>
                    Add Inventory
                </button>

            </Link>

            <hr />

            <h2>
                Inventory List
            </h2>

            <table
                border="1"
                cellPadding="10"
            >

                <thead>

                    <tr>

                        <th>ID</th>

                        <th>Item Name</th>

                        <th>Quantity</th>

                        <th>Actions</th>

                    </tr>

                </thead>

                <tbody>

                    {inventories.map(
                        (item) => (

                            <tr
                                key={item.id}
                            >

                                <td>
                                    {item.id}
                                </td>

                                <td>
                                    {item.itemName}
                                </td>

                                <td>
                                    {item.quantity}
                                </td>

                                <td>

                                    <button
                                        onClick={() =>
                                            reduceStock(
                                                item.itemName
                                            )
                                        }
                                    >
                                        Reduce Stock
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

export default InventoryPage;