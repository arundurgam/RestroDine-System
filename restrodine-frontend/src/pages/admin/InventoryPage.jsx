import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import {
    getInventory,
    updateInventory,
    reduceStock
} from "../../services/inventoryService";

function InventoryPage() {

    const [inventories, setInventories] =
        useState([]);

    const [search, setSearch] =
        useState("");

    useEffect(() => {

        loadInventory();

    }, []);

    const loadInventory = async () => {

        try {

            const data =
                await getInventory();

            setInventories(
                data
            );

        } catch (error) {

            console.error(error);

            alert(
                "Failed To Load Inventory"
            );
        }
    };

    const handleUpdate =
        async (item) => {

            const quantity =
                prompt(
                    "Enter New Quantity",
                    item.quantity
                );

            if (!quantity) {

                return;
            }

            try {

                await updateInventory(
                    item.itemName,
                    quantity
                );

                alert(
                    "Inventory Updated Successfully"
                );

                loadInventory();

            } catch (error) {

                console.error(error);

                alert(
                    "Update Failed"
                );
            }
        };

    const handleReduceStock =
        async (itemName) => {

            const quantity =
                prompt(
                    "Enter Quantity To Reduce"
                );

            if (!quantity) {

                return;
            }

            try {

                await reduceStock(
                    itemName,
                    quantity
                );

                alert(
                    "Stock Reduced Successfully"
                );

                loadInventory();

            } catch (error) {

                console.error(error);

                alert(
                    "Failed To Reduce Stock"
                );
            }
        };

    const filteredInventory =
        inventories.filter(
            (item) =>
                item.itemName
                    .toLowerCase()
                    .includes(
                        search.toLowerCase()
                    )
        );

    return (

        <div style={{ padding: "20px" }}>

            <h1>
                Inventory Management
            </h1>

            <hr />

            <Link to="/add-inventory">

                <button>
                    Add Inventory
                </button>

            </Link>

            <br />
            <br />

            <input
                type="text"
                placeholder="Search Item"
                value={search}
                onChange={(e) =>
                    setSearch(
                        e.target.value
                    )
                }
            />

            <br />
            <br />

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

                    {filteredInventory.map(
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
                                            handleUpdate(
                                                item
                                            )
                                        }
                                    >
                                        Update
                                    </button>

                                    {" "}

                                    <button
                                        onClick={() =>
                                            handleReduceStock(
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