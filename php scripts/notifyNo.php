<?php
require "conn.php";
 
$refNo=$_POST["ref_Number"];
$mysql_qry="select * from session,appointment where appointment.ref_No like '$refNo' and appointment.session_ID=session.session_ID;";
$result=mysqli_query($conn,$mysql_qry);
$row =mysqli_fetch_assoc($result);
$count=$row["counter"];
$nic=$row["doc_NIC"];


$doc_Name_Qry="select * from doctor where doc_NIC like '$nic';"; 
$result2=mysqli_query($conn,$doc_Name_Qry);
$row2=mysqli_fetch_assoc($result2);
$doc_Name=$row2["doc_Name"];

echo $count;

?>