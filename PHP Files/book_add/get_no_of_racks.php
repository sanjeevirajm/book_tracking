<?php
 $con=mysqli_connect("localhost","root","","book_tracking"); 

 if (mysqli_connect_errno($con)) 
 {   
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
 }
 else
 {	
	$no_of_racks = 0;
	
	$result = mysqli_query($con,"SELECT * FROM rack_details");
	while ($array = mysqli_fetch_array($result)) 
	{
	 $no_of_racks = $array['rack_no'];
	}

	echo "$no_of_racks";
  
 }
 mysqli_close($con);
?> 