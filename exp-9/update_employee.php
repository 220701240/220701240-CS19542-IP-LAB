
<?php
$servername = "localhost";
$username = "root";
$password = "student";
$database ="employee_details";

// Create connection
$conn = new mysqli($servername, $username, $password,$database);

// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}
echo "Connected successfully";
$empid = $_POST['empid'];
$desig =$_POST['desig'];

$sql = "UPDATE empdetails SET DESIG='$desig' WHERE EMPID='$empid'";
if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$sql = "SELECT * FROM EMPDETAILS";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        echo "EMPID: " . $row["EMPID"]. " - Name: " . $row["ENAME"]. " - Designation: " . $row["DESIG"].  " - DOJ: " . $row["DOJ"]. " - Salary: " . $row["SALARY"]. "<br>";
    }
} else {
    echo "0 results";
}
?>


