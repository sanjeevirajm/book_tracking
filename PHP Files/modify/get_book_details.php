<?php
 $con=mysqli_connect("localhost","root","","book_tracking"); 
 
 $flag_bar_qr = $_POST["flag_bar_qr"];
 $book_id = $_POST["book_id"]; 

 if (mysqli_connect_errno($con)) 
 {   
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
 }
 else
 {	
  if($flag_bar_qr == 1)
	$result = mysqli_query($con,"SELECT rack_no, shelf_no, order_no, title, author, publication, department, cost FROM bar_book_details where book_id='$book_id'");
  else
	$result = mysqli_query($con,"SELECT rack_no, shelf_no, order_no, title, author, publication, department, cost FROM qr_book_details where book_id='$book_id'");
  $row = mysqli_fetch_array($result);
  
  //$rack_no 
  echo $row[0]; 
  //$shelf_no = 
  echo "\n";
  echo $row[1];
  //$order_no = 
  echo "\n";
  echo $row[2];
  //$title =
  echo "\n";  
  echo $row[3];
  //$author = 
  echo "\n";
  echo $row[4];
  //$publication = 
  echo "\n";
  echo $row[5];
  //$department = 
  echo "\n";
  echo $row[6];
  //$cost = 
  echo "\n";
  echo $row[7];
 }
 mysqli_close($con);
?> 