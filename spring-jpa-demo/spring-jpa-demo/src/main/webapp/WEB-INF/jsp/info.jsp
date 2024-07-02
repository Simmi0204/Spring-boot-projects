<html>
<head>
    <title>Info Page for admin</title>
</head>
<body>
    <h2>${errorMsg}</h2>

    <h2>${successMsg}</h2>
    <form method="post" action="info">
        <label for="username">Name</label>
        <input type="text" name="username" id="username" required><br>
        <label for="password">password</label>
        <input type="password" name="password" id="password" required><br>
        <button>Submit</button>
    </form>
</body>
</html>