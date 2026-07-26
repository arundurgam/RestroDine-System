import { useNavigate } from "react-router-dom";

function Login() {
    const navigate = useNavigate();

    // ...login code...

    const role = data.role?.toUpperCase();

    if (role === "ADMIN") {
        navigate("/admin");
    } else if (role === "CUSTOMER") {
        navigate("/customer");
    } else if (role === "DELIVERY_AGENT") {
        navigate("/delivery");
    }

    return (
        <div>
            {/* Login Form */}
        </div>
    );
}

export default Login;