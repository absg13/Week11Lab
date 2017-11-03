<%-- 
    Document   : home
    Created on : Nov 3, 2017, 1:10:29 PM
    Author     : 738377
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SAIT Class Analyzer</title>
    </head>
    
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
            text-align: left;    
        }
        <%-- css styling from: 
            https://www.w3schools.com/html/tryit.asp?filename=tryhtml_table_colspan
        --%>
    </style>
    
    <body>
        <h1>SAIT Class Analyzer</h1>
        <h2>Add Class</h2>
        
        <div>
                <form action="home" method="POST">
                    <input type="hidden" name="pageName" id="pageName" value="add"/>
                    Class name: <input type="text" name="name" value="${name}"><br>
                    Class code: <input type="text" name="code" value="${code}"><br>
                    Current grade: <input type="text" name="currentgrade" value="${currentgrade}"><br>
                    Desired grade: <input type="text" name="desiredgrade" value="${desiredgrade}"><br>
                    <input type="submit" value="Add"><br><br>
            </form>
            </div> 
                    ${message} <br><br>
                
             
             <form action="home" method="POST">
             <input type="hidden" name="pageName" id="pageName" value="delete"/>

            <table style="width:25%">
               <tr>
                 <th>Name</th>
                 <th>Code</th> 
                 <th>Current Grade</th>
                 <th>Desired Grade</th>
               </tr>
               
               <c:forEach var="item" items="${classes}">
               <tr>
                 <td>${item.name}</td>
                 <td>${item.code}</td>
                 <td>${item.currentgrade}</td>
                 <td>${item.desiredgrade}</td>
                 <td><input type="radio" name="delete_${item.name}" value="${item.name}"></td>
               </tr>
               </c:forEach>
               
               <tr> <td><input type="submit" name="deletePost" value="Delete"></td></tr>
                   
             </table>
          </form>
    </body>
</html>
