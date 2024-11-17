<?php
// Database connection
$servername = "localhost";
$username = "root";
$password = "student";
$dbname = "hotelreservation";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Retrieve data from POST
$spaName = $_POST['spaName'];
$spaDate = $_POST['spaDate'];
$spaTime = $_POST['spaTime'];

// Update query to modify the booking for the specific name
$sql = "UPDATE spa_bookings SET date = ?, time = ? WHERE name = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("sss", $spaDate, $spaTime, $spaName);

if ($stmt->execute()) {
    echo "Booking updated successfully for " . $spaName . " to " . $spaDate . " at " . $spaTime;
} else {
    echo "Error: " . $stmt->error;
}

$stmt->close();
$conn->close();
?>
