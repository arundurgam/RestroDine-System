import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { register } from "../services/authService";

function Register() {

    const navigate =
        useNavigate();

    const [username, setUsername] =
        useState("");

    const [password, setPassword] =
        useState("");

    const [role, setRole] =
        useState("CUSTOMER");

    const handleRegister =
        async (e) => {

            e.preventDefault();

            try {

                await register({
                    username,
                    password,
                    role
                });

                alert(
                    "Registration Successful"
                );

                navigate(
                    "/login"
                );

            } catch (error) {

                console.error(error);

                alert(
                    "Registration Failed"
                );
            }
        };

    return (

        <div>

            <h1>
                Restaurant Management System
            </h1>

            <h2>
                Register
            </h2>

            <form
                onSubmit={handleRegister}
            >

                <input
                    placeholder="Username"
                    value={username}
                    onChange={(e) =>
                        setUsername(
                            e.target.value
                        )
                    }
                />

                <br /><br />

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) =>
                        setPassword(
                            e.target.value
                        )
                    }
                />

                <br /><br />

                <select
                    value={role}
                    onChange={(e) =>
                        setRole(
                            e.target.value
                        )
                    }
                >
                    <option value="ADMIN">
                        Admin
                    </option>

                    <option value="CUSTOMER">
                        Customer
                    </option>

                    <option value="DELIVERY_AGENT">
                        Delivery Agent
                    </option>

                </select>

                <br /><br />

                <button type="submit">
                    Register
                </button>

            </form>

            <br />

            <Link to="/login">
                Already Registered?
                Login
            </Link>

        </div>
    );
}

export default Register;