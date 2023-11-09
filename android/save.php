<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    require_once("db.php");

    $username = $_POST['username'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    $query = "INSERT INTO user (username, email, password) VALUES ('$username', '$email', '$password')";

    $result = $mysql->query($query);

    if ($result === TRUE) {
        echo "The user was created successfully";
    } else {
        echo "Error";
    }

    $mysql->close();
}
?>
