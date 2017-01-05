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
 $result = mysqli_query($con,"SELECT username, password FROM users_details where  username='$username' and password='$password'");
 $row = mysqli_fetch_array($result);
 $data = $row[0]; 
 if($data)
 {
	 echo "1"; //represents username and password are correct
 }
 else
 {
	 echo "0"; //represents username and password are wrong
 }
} 
 mysqli_close($con);
?> 