<?php
 $con=mysqli_connect("localhost","root","","book_tracking"); 
 
 $rack_no = $_POST["rack_no"];

 if (mysqli_connect_errno($con)) 
 {   
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
 }
 else
 {	
  $result = mysqli_query($con,"SELECT no_of_shelves FROM rack_details where rack_no='$rack_no'");
  $row = mysqli_fetch_array($result);
  $no_of_shelves = $row[0];
  echo "$no_of_shelves";
 }
 mysqli_close($con);
?> 