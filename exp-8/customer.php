<?php
include "create.php";

// Insert Customer
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $cname = $_POST['cname'];
    if (!empty($cname)) {
        $sql = "INSERT INTO CUSTOMER (CNAME) VALUES ('$cname')";
        if ($conn->query($sql) === TRUE) {
            echo "Customer added successfully";
        } else {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
    } else {
        echo "Customer name is required";
    }
}

// Display Customers
$sql = "SELECT * FROM CUSTOMER";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    echo "<h3>Customer List</h3><ul>";
    while($row = $result->fetch_assoc()) {
        echo "<li>ID: " . $row["CID"]. " - Name: " . $row["CNAME"]. "</li>";
    }
    echo "</ul>";
} else {
    echo "No customers found";
}
?>

