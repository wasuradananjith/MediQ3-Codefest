<?php
require "conn.php";
 
$refNo=$_POST["ref_Number"];
$mysql_qry="select * from session,appointment where appointment.ref_No like '$refNo' and appointment.session_ID=session.session_ID;";
$result=mysqli_query($conn,$mysql_qry);
$row =mysqli_fetch_assoc($result);
$count=$row["counter"];
$avgTime=$row["avg_Time"];
$app=$row["app_No"];
$nic=$row["doc_NIC"];


$doc_Name_Qry="select * from doctor where doc_NIC like '$nic';"; 
$result2=mysqli_query($conn,$doc_Name_Qry);
$row2=mysqli_fetch_assoc($result2);
$doc_Name=$row2["doc_Name"];

if ($count=="-1"){
	echo "Dr. ";
	echo $doc_Name." has JUST COME.";
}
else if($count=="-2"){
	echo "Dr. ";
	echo $doc_Name." is OUT at the moment.";
}
else if($count=="-3"){
	echo "Dr. ";
	echo $doc_Name."'s session is FINISHED.";
}
else if ($count=="0"){
	echo "Dr. ";
	echo $doc_Name."'s session has not yet started.";
}
else if ($count>$app){
	echo "Dr. ";
	echo $doc_Name."'s session has started.";
	echo " Current number is ".$count;
	echo ". Your appointment number which is ".$app;
	echo ", is OVER now.";
}
else if($app>$count){
	$time=($app-$count)*($avgTime);
	echo "Dr. ";
	echo $doc_Name."'s session has started.";
	echo " Current number is ".$count;
	echo ". Probably, your number will be called within next ".$time;
	echo " minutes.";
}
else if ($app==$count){
	echo "Dr. ";
	echo $doc_Name."'s session has started.";
	echo " Current number is ".$count;
	echo ", which means your appointment number. You must go in NOW.";
}
?>