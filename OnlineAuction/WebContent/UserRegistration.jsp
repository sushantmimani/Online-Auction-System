<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" media="all" />

<title>User Registration</title>
     
 </head>
    <body>
    <form action="RegistrationServlet" method="post">
 <div class="container">      	
		<center><h1>User Registration</h1></center>
           	<div  class="form">
    			<form id="contactform">	
	    			<p class="contact"><label for="name">First Name*</label></p> 
	    			<input id="name" name="fname" tabindex="1" type="text" required="required"> 
	    			
	    			<p class="contact"><label for="name">Last Name*</label></p> 
	    			<input id="name" name="lname"  tabindex="2" type="text" required="required"> 
	    			 
	    			<p class="contact"><label for="email">Email*</label></p> 
	    			<input id="email" name="email" placeholder="example@domain.com"   tabindex="3" type="email" required="required"> 
	                
	                <p class="contact"><label for="username">Username*</label></p> 
	    			<input id="username" name="username"  tabindex="4" type="text" required="required"> 
	    			 
	                <p class="contact"><label for="password">Password*</label></p> 
	    			<input type="password" id="password" name="password"  tabindex="5" required="required">
	    			 
	                <p class="contact"><label for="repassword">Re-enter password*</label></p> 
	    			<input type="password" id="repassword" name="repassword" tabindex="6" required="required"> <br><br>
	        
              
  
		            Gender:<br><br>
		            <input type="radio" name="sex" value="male" checked tabindex="7">Male&nbsp;&nbsp;&nbsp;
		            <input type="radio" name="sex" value="female" tabindex="8">Female&nbsp;&nbsp;&nbsp;
		            <input type="radio" name="sex" value="other" tabindex="9">Other<br><br>
		            
		            Register as:<br><br>
		            <input type="radio" name="usertype" value="buyer" checked tabindex="10">Buyer&nbsp;&nbsp;&nbsp;
		            <input type="radio" name="usertype" value="seller" tabindex="11">Seller&nbsp;&nbsp;&nbsp;
		            <input type="radio" name="usertype" value="both" tabindex="12">Both
		            <br><br>
		            
		            <p class="contact"><label for="phone">Mobile phone*</label></p> 
		            <input id="phone" name="phone" placeholder="Enter only numbers"  type="text" tabindex="13" required="required" maxlength="10"> <br><br>
		            		            
		            <p class="address"><label  for="address">Address</label></p> 
	    			<input id="streetname" name="streetname" placeholder="Street name*"  tabindex="14" type="text" required="required"> 	    			
	 	   			<input id="addLine1" name="addLine1" placeholder="Address Line1*" tabindex="15" type="text" required="required">
	 	   			<input id="addLine2" name="addLine2" placeholder="Address Line2" tabindex="16" type="text">
	 	   			<input id="city" name="city" placeholder="City*" tabindex="17" type="text" required="required">
	 	   			<input id="state" name="state" placeholder="State*" tabindex="18" type="text" required="required">
	 	   			<input id="zipcode" name="zipcode" placeholder="Zip Code*" tabindex="19" type="text" required="required" maxlength="5">
	 	   			<input id="country" name="country" placeholder="Country*" tabindex="20" type="text" required="required">
		            <br></br>
		            
		            <p class="creditcard"><label for="credit card"> Credit Card Details</label></p>
		            Name on Card*<br><br>
		            <input id="nameoncard" name="nameoncard" tabindex="21" type="text" required="required"><br>
		            Card Number*<br><br>	    			
	 	   			<input id="cardnum" name="cardnum" tabindex="22" type="text" required="required" maxlength="16"><br>
	 	   			Expiry Date<br><br>
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
					  </select><br><br>
	 	   			Security/CVV Number*<br><br>
	 	   			<input id="cvv" name="cvv" tabindex="23" type="password" maxlength="3" required="required">
	 	   			<br></br>
	 	   			
	 	   			<p class="bankaccount"><label for="bank account"> Bank Account Details</label></p>
		            Account Number*<br><br><input id="account" name="account" tabindex="24" type="text" required="required"><br>
		            Account Holder Name*<br><br><input id="account" name="accountname" tabindex="25" type="text" required="required"><br>
	 	   			Routing Number*<br><br><input id="routing" name="routing" tabindex="26" type="text" required="required"><br>
	 	   			Bank name*<br><br><input id="bank" name="bank" tabindex="27" type="text" required="required"><br>
	 	   			<br></br>
	 	   			
	 	   			
		            
		            <input class="buttom" name="submit" id="submit"  value="Sign me up!" type="submit" tabindex="28" > 	 
</form>
</div>
</div>
</form>    
</body>
</html>
    