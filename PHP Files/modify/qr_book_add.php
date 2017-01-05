<?php 
$con=mysqli_connect("localhost","root","","book_tracking"); 
if (mysqli_connect_errno($con)) 
{   
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
 }
	
 $book_id = $_POST['book_id'];
 $rack_no = $_POST['rack_no'];
 $shelf_no = $_POST['shelf_no'];
 $order_no = $_POST['order_no'];
 $title = $_POST['title'];
 $author = $_POST['author'];
 $publication = $_POST['publication'];
 $department = $_POST['department'];
 $cost = $_POST['cost']; 
 
 $sql="update qr_book_details set rack_no='$rack_no', shelf_no='$shelf_no', order_no='$order_no', title='$title', author='$author', publication='$publication', department='$department', cost='$cost' where book_id='$book_id'"; 
 
 echo "Modified";
 mysqli_query($con,$sql); 
 mysqli_close($con);

?> 