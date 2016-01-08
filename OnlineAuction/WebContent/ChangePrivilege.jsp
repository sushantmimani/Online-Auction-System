<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Privilege</title>
</head>
<body>
<h3>Your current privilege is <%=session.getAttribute("type")%></h3>

<form action="ChangePrivilege" method="post">
Change to:<br><br>
		            <input type="radio" name="usertype" value="buyer" checked tabindex="1">Buyer&nbsp;&nbsp;&nbsp;
		            <input type="radio" name="usertype" value="seller" tabindex="2">Seller&nbsp;&nbsp;&nbsp;
		            <input type="radio" name="usertype" value="both" tabindex="3">Both
		            <br><br>
                   <input type="submit" value="Update Privilege" />
                   </form> 
</body>
</html>