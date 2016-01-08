<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="javax.servlet.http.HttpServlet" 
import="javax.servlet.http.HttpServletRequest"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bid for your product</title>
</head>
<body>
<jsp:include page="/GetBidDetails" />
<h2> Product Name </h2>
<form action="ProductBid" method="post">
<% String Id = request.getParameter("Id");%>
<img src=GetPhoto?Id=<%=Id%> alt="Mountain View" style="width:304px;height:228px;"/>
<input id="prodid" name="prodid" type="hidden" value=<%=Id %> >
<input id="highestbid" name="highestbid" type="hidden" value=<%=request.getAttribute("highestbid") %> >
<input id="minprice" name="minprice" type="hidden" value=<%=request.getAttribute("minprice") %> >

<br></br>
	Minimimum Price <%=request.getAttribute("minprice") %>
	<br></br>
	Current Highest Bid <%=request.getAttribute("highestbid") %>
	<br></br>
	 Number of users who have bid <%=request.getAttribute("totalbids") %>
	<br></br>
	Auction Start Date <%=request.getAttribute("sdate") %><br/>
	Auction End Date <%=request.getAttribute("edate") %><br/>
	
<input id="bidvalue" name="bidvalue" placeholder="Bid Value"  tabindex="2" type="text" required="required">
 <br></br>
	   <input class="button" name="Submit" id="Submit"  value="Bid for the Product" type="Submit" tabindex="3" > 	 

</form>
</body>
</html>