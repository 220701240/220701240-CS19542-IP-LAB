<?php
include('create.php');

// Insert Account
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $atype = $_POST['atype'];
    $balance = $_POST['balance'];
    $cid = $_POST['cid'];

    // Validate inputs
    if (!empty($atype) && !empty($balance) && !empty($cid)) {
        if ($atype === 'S' || $atype === 'C') {
            $sql = "INSERT INTO ACCOUNT (ATYPE, BALANCE, CID) VALUES ('$atype', '$balance', '$cid')";
            if ($conn->query($sql) === TRUE) {
                echo "Account added successfully";
            } else {
                echo "Error: " . $sql . "<br>" . $conn->error;
            }
        } else {
            echo "Account type must be either S (Savings) or C (Current)";
        }
    } else {
        echo "All fields are required";
    }
}

// Display Accounts
$sql = "SELECT A.ANO, A.ATYPE, A.BALANCE, C.CNAME FROM ACCOUNT A INNER JOIN CUSTOMER C ON A.CID = C.CID";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    echo "<h3>Account List</h3><ul>";
    while($row = $result->fetch_assoc()) {
        echo "<li>Account No: " . $row["ANO"]. " - Type: " . $row["ATYPE"]. " - Balance: " . $row["BALANCE"]. " - Customer: " . $row["CNAME"]. "</li>";
    }
    echo "</ul>";
} else {
    echo "No accounts found";
}
?>

