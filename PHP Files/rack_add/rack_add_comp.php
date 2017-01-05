<?php 
 include("../common/check_connection.php");

 $no_of_racks = $_POST["no_of_racks"]; 	
 $no_of_shelves = $_POST["no_of_shelves"]; 
 $no_of_books = $_POST["no_of_books"];
 
  $rack_no = 0;
 
 $result = mysqli_query($con,"SELECT * FROM rack_details");
 while ($array = mysqli_fetch_array($result)) 
{
 $rack_no = $array['rack_no'];
 
}

/* $result = mysqli_query($con,"SELECT rack_no FROM rack_details");
 $array = mysqli_fetch_array($result);
 $i = 0;
 $rack_no = 1;
 
 while($array[$i] != null && $array[$i] != 'undefined') 
 {
	 echo $array[$i];
  $rack_no = $array[$i];
  $i++;
 }
 
 */
 //$rack_no = isset($_POST['rack_no']) ? $_POST['value'] : $rack_no = 0;
 
 $last_rack_no = $rack_no;
 echo $last_rack_no; 
 
 $total_no_of_racks = $last_rack_no + $no_of_racks;
  
 for($cur_rack_no = $last_rack_no + 1; $cur_rack_no <= $total_no_of_racks; $cur_rack_no++ )
 {
	 echo $cur_rack_no;
	 $sql="insert into rack_details values('$cur_rack_no', '$no_of_shelves', '$no_of_books')";
	 mysqli_query($con,$sql);
 }
 mysqli_close($con); /*
 $sql = "INSERT INTO rack_details VALUES('$no_of_racks','$no_of_shelves',$no_of_books)";
 //$sql='insert into rack_details values ('$no_of_racks', '$no_of_shelves', '$no_of_books')';
 
	 mysqli_query($con,$sql);
	 */
?> 