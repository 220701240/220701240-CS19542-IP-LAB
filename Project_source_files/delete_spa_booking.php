<?php
// Database connection
$servername = "localhost"; // Your database server
$username = "root"; // Your database username
$password = "student"; // Your database password
$dbname = "hotelreservation"; // Your database name

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Retrieve the data from POST
$spaDate = $_POST['spaDate'];
$spaTime = $_POST['spaTime'];

// Prepare SQL to delete the booking
$sql = "DELETE FROM spa_bookings WHERE date = ? AND time = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("ss", $spaDate, $spaTime);

if ($stmt->execute()) {
    if ($stmt->affected_rows > 0) {
        // Deletion successful
        echo "Booking deleted successfully for " . $spaDate . " at " . $spaTime;
    } else {
        // No matching booking found
        echo "No booking found for the specified date and time.";
    }
} else {
    // Error deleting the booking
    echo "Error: " . $stmt->error;
}

$stmt->close();
$conn->close();
?>
