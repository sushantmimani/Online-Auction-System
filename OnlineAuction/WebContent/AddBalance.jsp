<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Balance</title>
</head>
<jsp:include page="/GetCurrentDetails" />
<body>
Please confirm your card details and balance to be added:

<form action="AddBalance" method="post">
<p class="creditcard"><label for="credit card"> Credit Card Details</label></p>
		            Name on Card*<br/>
		            <input id="nameoncard" name="nameoncard" type="text" value=<%=request.getAttribute("cardname") %>><br/><br/>
		            Card Number*<br/>  			
	 	   			<input id="cardnum" name="cardnum" type="text" value=<%=request.getAttribute("cardnum")%>><br/><br/>
	 	   			Expiry Date<br/>
	 	   			Month&nbsp;&nbsp;&nbsp;					
				    <select name="expmonth" > 
					  <option value="01">01</option>
					  <option value="02">02</option>
					  <option value="03">03</option>
					  <option value="04">04</option>
					  <option value="05">05</option>
					  <option value="06">06</option>
					  <option value="07">07</option>
					  <option value="08">08</option>
					  <option value="09">09</option>
					  <option value="10">10</option>
					  <option value="11">11</option>
					  <option value="12">12</option> 
					</select>&nbsp;&nbsp;&nbsp;
					Year&nbsp;&nbsp;&nbsp;
					<select name="expyear">
					  <option value="2005">2005</option>
					  <option value="2006">2006</option>
					  <option value="2007">2007</option>
					  <option value="2008">2008</option>
					  <option value="2009">2009</option>
					  <option value="2010">2010</option>
					  <option value="2011">2011</option>
					  <option value="2012">2012</option>
					  <option value="2013">2013</option>
					  <option value="2014">2014</option>
					  <option value="2015">2015</option>
					  <option value="2016">2016</option>
					  <option value="2017">2017</option>
					  <option value="2018">2018</option>
					  <option value="2019">2019</option>
					  <option value="2020">2020</option>
					  <option value="2021">2021</option>
					  <option value="2022">2022</option>
					  <option value="2023">2023</option>
					  <option value="2024">2024</option>
					  <option value="2025">2025</option>
					  </select><br/><br/>
	 	   			Security/CVV Number*<br>
	 	   			<input id="cvv" name="cvv" tabindex="3" type="password" maxlength="3" required="required">
	 	   			<br/><br/>
	 	   			Balance to be added:<br/>
	 	   			<input id="balance" name="balance" tabindex="4" type="text" required="required"><br>
	 	   			<br/>
	 	   			<input type = "submit" value = "Add Balance">
	 	 </form>

</body>
</html>