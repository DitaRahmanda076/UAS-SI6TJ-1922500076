<?php
$con = new mysqli ("localhost","root","","campusS");
if ($con -> connect_errno){
	echo "Failed to connect to MySQL: " . $con -> connect_error;
	exit()	;
}
?>