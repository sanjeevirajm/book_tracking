<?php 
$con=mysqli_connect("localhost","root","","book_tracking"); 
if (mysqli_connect_errno($con)) 
{   
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
 }
else
{	
 $username = urldecode($_POST['username']);
 $password = urldecode($_POST['password']); 
 
 $result = mysqli_query($con,"SELECT access_rack_add, access_book_add, access_modify, access_search, access_arrange FROM users_details where  username='$username' and password='$password'");
 $row = mysqli_fetch_array($result);
 
 $access_rack_add = $row[0];
 $access_book_add = $row[1];
 $access_modify = $row[2];
 $access_search = $row[3];
 $access_arrange = $row[4]; 
 
 echo "$access_rack_add";
 echo "$access_book_add";
 echo "$access_modify";
 echo "$access_search";
 echo "$access_arrange";
 
} 
 mysqli_close($con);
?> 