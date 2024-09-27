<?php
include('create.php');

// Insert Transaction
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $ano = $_POST['ano'];
    $ttype = $_POST['ttype'];
    $tamount = $_POST['tamount'];
    
    if (!empty($ano) && !empty($ttype) && !empty($tamount)) {
        // Check account balance for withdrawals
        if ($ttype === 'W') {
            $balance_check = "SELECT BALANCE FROM ACCOUNT WHERE ANO = '$ano'";
            $result = $conn->query($balance_check);
            if ($result->num_rows > 0) {
                $row = $result->fetch_assoc();
                if ($row['BALANCE'] >= $tamount) {
                    $sql = "INSERT INTO TRANSACTION (ANO, TTYPE, TDATE, TAMOUNT) VALUES ('$ano', '$ttype', NOW(), '$tamount')";
                    if ($conn->query($sql) === TRUE) {
                        $update_balance = "UPDATE ACCOUNT SET BALANCE = BALANCE - $tamount WHERE ANO = '$ano'";
                        $conn->query($update_balance);
                        echo "Transaction successful";
                    } else {
                        echo "Error: " . $sql . "<br>" . $conn->error;
                    }
                } else {
                    echo "Insufficient balance for withdrawal";
                }
            }
        } elseif ($ttype === 'D') {
            $sql = "INSERT INTO TRANSACTION (ANO, TTYPE, TDATE, TAMOUNT) VALUES ('$ano', '$ttype', NOW(), '$tamount')";
            if ($conn->query($sql) === TRUE) {
                $update_balance = "UPDATE ACCOUNT SET BALANCE = BALANCE + $tamount WHERE ANO = '$ano'";
                $conn->query($update_balance);
                echo "Deposit successful";
            } else {
                echo "Error: " . $sql . "<br>" . $conn->error;
            }
        } else {
            echo "Invalid transaction type (D for Deposit, W for Withdrawal)";
        }
    } else {
        echo "All fields are required";
    }
}
?>

