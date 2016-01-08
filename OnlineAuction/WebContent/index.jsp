<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>Login Application</title>  
</head>  
<body>  
    <form action="LoginServlet" method="post">  
        <fieldset style="width: 300px">  
            <legend> Login to site </legend>  
            <table>  
                <tr>  
                    <td>User ID</td>  
                    <td><input type="text" name="username" required="required" /></td>  
                </tr>  
                <tr>  
                    <td>Password</td>  
                    <td><input type="password" name="userpass" required="required" /></td>  
                </tr>  
                <tr>  
                    <td><input type="submit" value="Login" /></td>   
                    </form>
                    <form action="UserRegistration.jsp" method="get">
                    <td><input type="submit" value="Register" /></td> 
                    </form>                               
                </tr>  
            </table>  
        </fieldset>  
 </form>
</body>  
</html> 