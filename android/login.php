<?php

$con = mysqli_connect("localhost", "root", "", "usuario");

if (isset($_POST["username"]) && isset($_POST["password"])) {
    $username = $_POST["username"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);

    mysqli_stmt_execute($statement);

    $result = mysqli_stmt_store_result($statement);

    if ($result) {
        mysqli_stmt_bind_result($statement, $user_id, $username, $email, $password);

        $response = array();
        $response["success"] = false;

        while (mysqli_stmt_fetch($statement)) {
            $response["success"] = true;
            $response["username"] = $username;
            $response["email"] = $email;
            $response["password"] = $password;
        }

        echo json_encode($response);
    } else {

        $response = array("success" => false, "error" => "Error en la consulta");
        echo json_encode($response);
    }

    mysqli_stmt_close($statement);
} else {
    $response = array("success" => false, "error" => "Los valores 'username' y 'password' son requeridos.");
    echo json_encode($response);
}

mysqli_close($con);

?>