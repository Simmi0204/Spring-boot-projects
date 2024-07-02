<html>
<head>
<title>Registration page</title>
</head>
<body>
	
	<h1>Register here</h1>
	<h2>Add credentials</h2>

<form method="post" action="/register">
	<label for="username">name</label> <input type="text" name="username" id="username" required><br> <label for="password">password</label>
	<input type="password" name="password" id="password" required><br>

	<h2>Add Address details</h2>
	
		<br> <label for="addressLine1">Address Line 1:</label> 
        <input type="text" name="addressLine1" id="addressLine1"><br>
	    <br> <label for="addressLine2">Address Line 2:</label> 
        <input type="text" id="addressLine2" name="addressLine2"><br> 
        <br> <label for="landmark">Landmark:</label> 
        <input type="text" id="landmark" name="landmark"><br> 
        <br> <label for="city">City:</label> 
        <input type="text" id="city" name="city"><br>
	    <br> <label for="pincode">Pincode:</label> 
        <input type="text" id="pincode" name="pincode"><br> 
        <br> <label for="state">State:</label> 
        <input type="text" id="state" name="state"><br>
	    <br> <label for="country">Country:</label> 
        <input type="text" id="country" name="country"><br> <br>

	<button>submit</button>
</form>
</body>
</html>