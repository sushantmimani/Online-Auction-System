<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm Address</title>
</head>
<body>
<jsp:include page="/GetCurrentDetails" />
<h5> Confirm Address:</h5>
<form action="UpdateAddressDetails" method = "post">
<p class="address"><label  for="address"></label></p> 
<input id="streetname" name="streetname" value=<%=request.getAttribute("street")%>  tabindex="1" type="text" required="required"> <br/><br/>	    			
<input id="addLine1" name="addLine1" value=<%=request.getAttribute("addressline1")%> tabindex="2" type="text" required="required"><br/><br/>
<input id="addLine2" name="addLine2" tabindex="3" type="text" value=<%=request.getAttribute("addressline2")%>  ><br/><br/>
<input id="city" name="city" value=<%=request.getAttribute("city")%> tabindex="4" type="text" required="required"><br/><br/>
<input id="state" name="state" value=<%=request.getAttribute("state")%> tabindex="5" type="text" required="required"><br/><br/>
<input id="zipcode" name="zipcode" value=<%=request.getAttribute("zipcode")%> tabindex="6" type="text" required="required" maxlength="5"><br/><br/>
<input id="country" name="country" value=<%=request.getAttribute("country")%> tabindex="7" type="text" required="required">
<input type="hidden" name="auctionid" value=<%=request.getParameter("auctionid") %>>
<input name="accept" value=<%=request.getParameter("accept")%> tabindex="7" type="hidden">
<br></br>
<input type=submit value="Confirm Address"></form>
</body>
</html>