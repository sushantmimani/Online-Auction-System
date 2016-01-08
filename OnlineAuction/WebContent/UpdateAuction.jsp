<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Auction</title>
</head>
<body>
<center><h1>Update Auction</h1></center>
<form action="ChangeAuction" method="post" >
	   <p class="prodName"><label for="Product Name">Product Name</label></p> 
	    <input id="prodName" name="prodName" value=<%=request.getAttribute("name") %>  tabindex="1" type="text" required="required">
 	   	   
	   <p class=prodDesc><label for="Product Description">Product Description</label></p>
	   <input id="prodDesc" name="prodDesc" placeholder="Product Description" tabindex="2"  type="text" required="required">
       
       <p class=minPrice><label for="MinPrice">Minimum Price</label></p>
	   <input id="minPrice" name="minPrice" value=<%=request.getAttribute("minprice") %> tabindex="3"  type="text" required="required">
       
       <p class="startDate"><label for="Start Date">Start Date</label></p> 
	    <input id="startDate" name="startDate" value=<%=request.getAttribute("startdate") %>  tabindex="4" type="date" required="required">
	    
	    <p class="endDate"><label for="End Date">End Date</label></p> 
	    <input id="endDate" name="endDate" value=<%=request.getAttribute("enddate") %>  tabindex="5" type="date" required="required">
	    <input type="hidden" name="auctionid" value="<%=request.getAttribute("auctionid") %>" />
	    <input type="hidden" name="prodid" value="<%=request.getAttribute("prodid") %>" />
	    <input type="hidden" name="active" value="<%=request.getAttribute("active") %>" />
	   	    
       <br></br>
	   <input class="buttom" name="Submit" id="Submit"  value="Update Auction" type="submit" tabindex="8" > 	 

</form>
</body>
</html>