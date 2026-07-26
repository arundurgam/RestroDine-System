import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import {
    getRestaurants,
    deleteRestaurant,
    updateRestaurant
} from "../../services/restaurantService";

function RestaurantsPage() {

    const [restaurants, setRestaurants] =
        useState([]);

    const [search, setSearch] =
        useState("");

    useEffect(() => {

        loadRestaurants();

    }, []);

    const loadRestaurants =
        async () => {

            try {

                const data =
                    await getRestaurants();

                setRestaurants(data);

            } catch (error) {

                console.error(error);

                alert(
                    "Failed To Load Restaurants"
                );
            }
        };

    const handleDelete =
        async (id) => {

            const confirmDelete =
                window.confirm(
                    "Delete Restaurant?"
                );

            if (!confirmDelete) {

                return;
            }

            try {

                await deleteRestaurant(id);

                alert(
                    "Restaurant Deleted Successfully"
                );

                loadRestaurants();

            } catch (error) {

                console.error(error);

                alert(
                    "Delete Failed"
                );
            }
        };

    const handleUpdate =
        async (restaurant) => {

            const name =
                prompt(
                    "Restaurant Name",
                    restaurant.name
                );

            const location =
                prompt(
                    "Location",
                    restaurant.location
                );

            const cuisineType =
                prompt(
                    "Cuisine Type",
                    restaurant.cuisineType
                );

            if (
                !name ||
                !location ||
                !cuisineType
            ) {

                return;
            }

            try {

                await updateRestaurant(
                    restaurant.id,
                    {
                        name,
                        location,
                        cuisineType
                    }
                );

                alert(
                    "Restaurant Updated Successfully"
                );

                loadRestaurants();

            } catch (error) {

                console.error(error);

                alert(
                    "Update Failed"
                );
            }
        };

    const filteredRestaurants =
        restaurants.filter(
            (restaurant) =>
                restaurant.name
                    .toLowerCase()
                    .includes(
                        search.toLowerCase()
                    )
        );

    return (

        <div style={{ padding: "20px" }}>

            <h1>
                Restaurant Management
            </h1>

            <Link to="/add-restaurant">

                <button>
                    Add Restaurant
                </button>

            </Link>

            <br />
            <br />

            <input
                type="text"
                placeholder="Search Restaurant"
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

                        <th>Name</th>

                        <th>Location</th>

                        <th>Cuisine</th>

                        <th>Status</th>

                        <th>Actions</th>

                    </tr>

                </thead>

                <tbody>

                    {filteredRestaurants.map(
                        (restaurant) => (

                            <tr
                                key={
                                    restaurant.id
                                }
                            >

                                <td>
                                    {restaurant.id}
                                </td>

                                <td>
                                    {restaurant.name}
                                </td>

                                <td>
                                    {restaurant.location}
                                </td>

                                <td>
                                    {restaurant.cuisineType}
                                </td>

                                <td>
                                    {restaurant.status}
                                </td>

                                <td>

                                    <button
                                        onClick={() =>
                                            handleUpdate(
                                                restaurant
                                            )
                                        }
                                    >
                                        Update
                                    </button>

                                    {" "}

                                    <button
                                        onClick={() =>
                                            handleDelete(
                                                restaurant.id
                                            )
                                        }
                                    >
                                        Delete
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

export default RestaurantsPage;