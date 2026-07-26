const role =
    data.role?.toUpperCase();

if (role === "ADMIN") {

    navigate("/admin");

}
else if (role === "CUSTOMER") {

    navigate("/customer");

}
else if (
    role === "DELIVERY_AGENT"
) {

    navigate("/delivery");

}