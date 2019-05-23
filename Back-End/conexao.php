<?php
	$con = mysqli_connect('localhost', '', '', '') or die ('erro de conexao');
	mysqli_set_charset($con, 'utf8');
	mysqli_query($con, "SET NAMES 'utf8'");
    mysqli_query($con, 'SET character_set_connection=utf8');
    mysqli_query($con, 'SET character_set_client=utf8');
    mysqli_query($con, 'SET character_set_results=utf8');
?>
