<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Phone Number</title>
</head>
<body>
<jsp:include page="/GetCurrentDetails" />
<h5> Your current phone number is:</h5><br/>
<%=request.getAttribute("mobile") %>
<form action="UpdatePhoneNumber" method = "post">
<h5> Enter updated phone number</h5>
<form action="UpdateAddressDetails" method = "post">
<input id="phone" name="phone" placeholder="Enter only numbers"  type="text" tabindex="13" required="required" maxlength="10"> <br><br>
<input type=submit value="Update Phone"></form>
</body>
</html>