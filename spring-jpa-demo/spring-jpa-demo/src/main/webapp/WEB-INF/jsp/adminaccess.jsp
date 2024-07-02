<html>
<head>
    <title>Admin specific access to information</title>
</head>
<body>
    <h2>${successMsg}</h2>

    <form method="post" action="adminaccess">
        <label for="state">Enter state name to get users</label>
        <input type="text" name="state" id="state"><br>

        <button>Submit</button>
    </form>
</body>
</html>