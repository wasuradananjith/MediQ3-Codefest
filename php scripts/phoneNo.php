<?php
require "conn.php";
 
$phoneNo="771234567";
$mysql_qry="select * from patient where patient_Phone like '$phoneNo';";
$result=mysqli_query($conn,$mysql_qry);


if (mysqli_num_rows($result)>0){
echo "Login Success";
}
else{
	echo "Sorry you do not have any appointment";
}
?>