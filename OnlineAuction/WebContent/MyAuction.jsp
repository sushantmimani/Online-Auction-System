<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Auction</title>
</head>
<body>
<h2> My Auction </h2>
<% ArrayList<Integer> auctionid = (ArrayList<Integer>) request.getAttribute("auctionid");
ArrayList<Integer> prodid = (ArrayList<Integer>) request.getAttribute("prodid");
ArrayList<Float> minprice = (ArrayList<Float>) request.getAttribute("minprice");
ArrayList<String> name = (ArrayList<String>) request.getAttribute("name");
ArrayList<String> active = (ArrayList<String>) request.getAttribute("active");
ArrayList<Date> startdate = (ArrayList<Date>) request.getAttribute("startdate");
ArrayList<Date> enddate = (ArrayList<Date>) request.getAttribute("enddate");
int i = 0;
int length = auctionid.size();%>
<table>
                <thead>
                    <tr>
                        <th>Auction ID</th>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Minimum Price</th>
           				<th>Start Date</th>
           				<th>End Date</th>
           				<th>Active</th>
           				<th>Update</th>
           				<th>Delete Auction</th>
                    </tr>
                </thead>
                <tbody>
             <% 
			 for(i = 0;i<length;i++) {%>   
                    <tr>
                        <td><%=auctionid.get(i)%></td>
                        <td><%=prodid.get(i)%></td>
                        <td><%=name.get(i) %></td>
                        <td><%=minprice.get(i) %></td>
                        <td><%=startdate.get(i) %></td>
                        <td><%=enddate.get(i) %></td>
                        <td><%=active.get(i) %></td>
                        <td> <form action="UpdateAuction" method="get">
                   <input type="hidden" name="auctionid" value="<%=auctionid.get(i)%>" />
                   <input type="hidden" name="prodid" value="<%=prodid.get(i)%>" />
                   <input type="hidden" name="name" value="<%=name.get(i)%>" />
                   <input type="hidden" name="minprice" value="<%=minprice.get(i)%>" />
                   <input type="hidden" name="startdate" value="<%=startdate.get(i)%>" />
                   <input type="hidden" name="enddate" value="<%=enddate.get(i)%>" />
                   <input type="hidden" name="active" value="<%=active.get(i)%>" />
                   <input type="submit" name="update" value="Update" />
                   </form>  </td>
                   		<td> <form action="UpdateAuction" method="get">
                   <input type="hidden" name="auctionid" value="<%=auctionid.get(i)%>" />
                   <input type="hidden" name="prodid" value="<%=prodid.get(i)%>" />
                   <input type="hidden" name="name" value="<%=name.get(i)%>" />
                   <input type="hidden" name="minprice" value="<%=minprice.get(i)%>" />
                   <input type="hidden" name="startdate" value="<%=startdate.get(i)%>" />
                   <input type="hidden" name="enddate" value="<%=enddate.get(i)%>" />
                   <input type="hidden" name="active" value="<%=active.get(i)%>" />
                   <input type="submit" name="delete" value="Delete Auction" />
                   </form>  </td>
                    </tr>
                    <%} %>
                </tbody>
            </table>
</body>
</html>