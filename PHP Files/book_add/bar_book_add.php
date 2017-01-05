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
 
 $sql="insert into bar_book_details values('$book_id', '$rack_no', '$shelf_no', '$order_no', '$title', '$author', '$publication', '$department', '$cost')";
 
 echo "Book added";
 mysqli_query($con,$sql); 
 mysqli_close($con);

?> 