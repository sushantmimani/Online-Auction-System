<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" media="all" />
<title> Create Auction</title>
</head>
<body>
<center><h1>Auction Details</h1></center>
<form action="CreateAuction" method="post" enctype="multipart/form-data">
	   <p class="prodName"><label for="Product Name">Product Name</label></p> 
	    <input id="prodName" name="prodName" placeholder="Product name"  tabindex="1" type="text" required="required">
	  
	   <p class=prodImage><label for="Product Image">Product Image</label></p>
	   <input type="file" name="photo" accept="image/*" tabindex="2">
 	   	   
	   <p class=prodDesc><label for="Product Description">Product Description</label></p>
	   <input id="prodDesc" name="prodDesc" placeholder="Product Description" tabindex="3"  type="text" required="required">
       
       <p class=minPrice><label for="MinPrice">Minimum Price</label></p>
	   <input id="minPrice" name="minPrice" placeholder="Minimum Price" tabindex="4"  type="text" required="required">
       
       <p class="startDate"><label for="Start Date">Start Date</label></p> 
	    <input id="startDate" name="startDate" placeholder="Start Date"  tabindex="5" type="date" required="required">
	    
	    <p class="endDate"><label for="End Date">End Date</label></p> 
	    <input id=""endDate"" name="endDate" placeholder="End Date"  tabindex="6" type="date" required="required">
	    
	   	    
       <br></br>
	   <input class="buttom" name="Submit" id="Submit"  value="Create Auction" type="Submit" tabindex="7" > 	 

</form>
</body>
</html>