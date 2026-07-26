import { Link } from "react-router-dom";

function AdminDashboard() {

    return (

        <div style={{ padding: "20px" }}>

            <h1>
                Admin Dashboard
            </h1>

            <hr />

            <div
                style={{
                    display: "flex",
                    gap: "20px",
                    flexWrap: "wrap",
                    marginBottom: "30px"
                }}
            >

                <div
                    style={{
                        border: "1px solid #ddd",
                        padding: "20px",
                        width: "220px",
                        borderRadius: "10px"
                    }}
                >
                    <h3>
                        Restaurants
                    </h3>

                    <p>
                        Manage Restaurant Operations
                    </p>
                </div>

                <div
                    style={{
                        border: "1px solid #ddd",
                        padding: "20px",
                        width: "220px",
                        borderRadius: "10px"
                    }}
                >
                    <h3>
                        Inventory
                    </h3>

                    <p>
                        Manage Stock & Inventory
                    </p>
                </div>

                <div
                    style={{
                        border: "1px solid #ddd",
                        padding: "20px",
                        width: "220px",
                        borderRadius: "10px"
                    }}
                >
                    <h3>
                        Orders
                    </h3>

                    <p>
                        Track Customer Orders
                    </p>
                </div>

                <div
                    style={{
                        border: "1px solid #ddd",
                        padding: "20px",
                        width: "220px",
                        borderRadius: "10px"
                    }}
                >
                    <h3>
                        Deliveries
                    </h3>

                    <p>
                        Manage Deliveries
                    </p>
                </div>

            </div>

            <hr />

            <h2>
                Restaurant Management
            </h2>

            <Link to="/restaurants-management">
                Restaurant Management
            </Link>

            <br /><br />

            <h2>
                Inventory Management
            </h2>

            <Link to="/inventory">
                Inventory Management
            </Link>

            <br /><br />

            <h2>
                Order Management
            </h2>

            <Link to="/orders">
                Order Management
            </Link>

            <br /><br />

            <h2>
                Delivery Management
            </h2>

            <Link to="/deliveries">
                Delivery Management
            </Link>

            <br /><br />

            <hr />

            <Link to="/logout">
                Logout
            </Link>

        </div>
    );
}

export default AdminDashboard;