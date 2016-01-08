<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1" import="java.io.*" import="java.sql.*" %>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>Welcome <%=session.getAttribute("name")%></title>  
</head>  
<body>  
    <h3>Login successful!!!</h3>  
    <h4>  
        Hello,  
        <%=session.getAttribute("name")%></h4> 
        Current Account Balance  <%=session.getAttribute("balance")%><br/>
        <%
      int id = (Integer)session.getAttribute("id");
        try {
            Connection conn = null;  
	        PreparedStatement pst = null;  
	        ResultSet rs = null; 
	        
	       Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager  
                    .getConnection("jdbc:mysql://localhost:3306/form", "root", "nainital"); 
            
	   String query = "select prodid from prodtype where prodid in (select prodid from auction where active=1)";
	//  pst.setInt(1, id);
	  pst = conn.prepareStatement(query);
	    rs = pst.executeQuery();
	    if(rs != null)
	    {
	    while(rs.next())
	    {
	    	int Id = rs.getInt(1); %>
	    	<a href="productbid.jsp?Id=<%=Id%>">
	        <img src=GetPhoto?Id=<%=Id%> alt="Mountain View" style="width:304px;height:228px;"
	 onError="loadImage()" onAbort="loadImage()" />
	 </a>

	 <% 
	    
	    }
	    }  else %> No live auctions <%
	  
	    }
	        catch (SQLException e) {
	        	e.printStackTrace();
		  } %>
		  
<br/><br/>
<form action="CreateAuction.jsp" method="get">
                   <input type="submit" value="Create Auction" />
                   </form> 
<form action="UpdateAccount.jsp" method="get">
                   <input type="submit" value="Update Account Details" />
                   </form>  
      <form action="MyAuctions" method="get">
                   <input type="submit" value="My Auction" />
                   </form>  
      <form action="MyBids" method="get">
                   <input type="submit" value="My Bids" />
                   </form>     
        
</body>  
</html>