<html>
<head>
<title>Simple Login Form</title>
</head>
<body>
<h2> ${errorMSG} </h2>
<h2>${successMSG}</h2>
			
	<form method="post" action ="/welcome">
		<label for="username">name</label>
		<input type="text" name="username" id="username" required><br><br>
		<label for="password">password</label>
		<input type="password" name="password" id="password" required><br><br>
		<button>submit</button><br>
	</form>
	<a href="/register">
	    <button>register</button>
	</a>
</body>
</html>
		
			<%-- ${errorMSG} will work only when the controller sees if condition fails and modelmap obj is used --%>