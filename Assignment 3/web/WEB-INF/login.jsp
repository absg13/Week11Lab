<%-- 
    Document   : login
    Created on : Nov 13, 2017, 3:53:29 PM
    Author     : 738377
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NotesKeepr</title>
    </head>
    <body>
        <h1>NotesKeepr Login</h1>
        <div>
                <form action="login" method="POST">
                    Username: <input type="text" name="username" value="${username}"><br>
                    Password: <input type="password" name="password" value="${password}"><br>
                    <input type="submit" value="Login"><br>
                </form>
            </div> 
                ${message} 
    </body>
</html>
