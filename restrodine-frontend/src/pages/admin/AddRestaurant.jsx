import { useState } from "react";
import axios from "axios";

function AddRestaurant() {

    const [name, setName] = useState("");
    const [location, setLocation] = useState("");
    const [cuisineType, setCuisineType] = useState("");

    const addRestaurant = async () => {

        try {

            const token =
                localStorage.getItem("token");

            await axios.post(
                "http://localhost:8080/restaurants",
                {
                    name,
                    location,
                    cuisineType
                },
                {
                    headers: {
                        Authorization:
                            `Bearer ${token}`
                    }
                }
            );

            alert(
                "Restaurant Added Successfully"
            );

            setName("");
            setLocation("");
            setCuisineType("");

        } catch (error) {

            console.error(error);

            alert(
                "Failed To Add Restaurant"
            );
        }
    };

    return (

        <div>

            <h1>Add Restaurant</h1>

            <input
                placeholder="Restaurant Name"
                value={name}
                onChange={(e) =>
                    setName(e.target.value)
                }
            />

            <br /><br />

            <input
                placeholder="Location"
                value={location}
                onChange={(e) =>
                    setLocation(e.target.value)
                }
            />

            <br /><br />

            <input
                placeholder="Cuisine Type"
                value={cuisineType}
                onChange={(e) =>
                    setCuisineType(e.target.value)
                }
            
            />

            <br /><br />

            <button
                onClick={addRestaurant}
            >
                Add Restaurant
            </button>

        </div>
    );
}

export default AddRestaurant;