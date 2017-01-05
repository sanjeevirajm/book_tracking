<?php 
 $con=mysqli_connect("localhost","root","","book_tracking"); 
 
 $username = $_POST["username"];
 $password = $_POST["password"]; 
 $no_of_racks = $_POST["no_of_racks"]; 	
 $no_of_shelves = $_POST["no_of_shelves"]; 
 $no_of_books = $_POST["no_of_books"];
 if (mysqli_connect_errno($con)) 
 {   
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
   //echo "0";  //indicates problem with database
 }
 else
 {	
  $result = mysqli_query($con,"SELECT username, password FROM users_details where  Username='$username' and Password='$password'");
  $row = mysqli_fetch_array($result);
  if($username == $row[0] && $password == $row[1])
  {
	$rack_no = 0;
 
	$result = mysqli_query($con,"SELECT * FROM rack_details");
	while ($array = mysqli_fetch_array($result)) 
	{
	$rack_no = $array['rack_no'];
	}
	$last_rack_no = $rack_no;
	//echo $last_rack_no; 
 
	$total_no_of_racks = $last_rack_no + $no_of_racks;
  
	for($cur_rack_no = $last_rack_no + 1; $cur_rack_no <= $total_no_of_racks; $cur_rack_no++ )
	{
	 //echo $cur_rack_no;
	 $sql="insert into rack_details values('$cur_rack_no', '$no_of_shelves', '$no_of_books')";
	 mysqli_query($con,$sql);
	}
	echo "1";
	mysqli_close($con);  
  }
  else
  {
	echo "2";
	//indicates uname and password is incorrect
  }
 }
?> 