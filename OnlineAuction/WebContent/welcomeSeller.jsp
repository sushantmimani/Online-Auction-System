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
        
       

<br> </br>
<form action="CreateAuction.jsp" method="get">
                   <input type="submit" value="Create Auction" />
                   </form> 
<form action="UpdateAccount.jsp" method="get">
                   <input type="submit" value="Update Account Details" />
                   </form>  
      <form action="MyAuctions" method="get">
                   <input type="submit" value="My Auction" />
                   </form>      
        
</body>  
</html>