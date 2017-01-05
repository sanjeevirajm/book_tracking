<?php
 $con=mysqli_connect("localhost","root","","book_tracking"); 
 
 $flag_bar_qr = $_POST["flag_bar_qr"];
 $rack_no = $_POST["rack_no"];
 $shelf_no = $_POST["shelf_no"];
 $order_no = $_POST["order_no"]; 

 if (mysqli_connect_errno($con)) 
 {   
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
 }
 else
 {	
  if($flag_bar_qr == 1)
	$result = mysqli_query($con,"SELECT book_id FROM bar_book_details where rack_no='$rack_no' && shelf_no='$shelf_no' && order_no='$order_no'");
  else
	$result = mysqli_query($con,"SELECT book_id FROM qr_book_details where rack_no='$rack_no' && shelf_no='$shelf_no' && order_no='$order_no'");
  $row = mysqli_fetch_array($result);
  
  //$book_id 
  echo $row[0]; 
  
 }
 mysqli_close($con);
?> 