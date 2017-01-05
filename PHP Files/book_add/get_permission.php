<?php
 $con=mysqli_connect("localhost","root","","book_tracking"); 
 
 $username = $_POST["username"];
 $password = $_POST["password"]; 

 if (mysqli_connect_errno($con)) 
 {   
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
 }
 else
 {	
  $result = mysqli_query($con,"SELECT username, password FROM users_details where  Username='$username' and Password='$password'");
  $row = mysqli_fetch_array($result);
  if($username == $row[0] && $password == $row[1])
  {
	 //echo "Connected : username and password are correct";
	 $result = mysqli_query($con,"SELECT access_book_add FROM users_details where  username='$username' and password='$password'");
	 $row = mysqli_fetch_array($result);
	 $access_book_add = $row[0];
	 echo $access_book_add;
  }
  else
  {
	echo "2";
	//indicates uname and password is incorrect
  }
 }
 mysqli_close($con);
?> 