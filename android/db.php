<?php

    $mysql=new mysqli(
        "localhost",
        "root",
        "",
        "usuario"
    );

    if($mysql->connect_error){
        die("Failed to connect" . $mysql->connect_error);
    }else{
        echo "Successfully";
    }