<%-- 
    Document   : notes
    Created on : Nov 15, 2017, 9:19:19 PM
    Author     : 738377
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Notes</title>
        <link rel="stylesheet" href="<c:url value='styles/notes.css' />" />
    </head>
    <body>
        <h1>Manage Notes</h1>
        <h2>Notes</h2>
        <p>${errorMessage}</p>
        <table>
            <tr>
                <th>Note ID</th>
                <th>Date Created</th>
                <th>Title</th>
                <th>Contents</th>
                <th>Owner</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach var="note" items="${notes}">
                <tr>
                    <td>${note.noteID}</td>
                    <td>${note.dateCreated}</td>
                    <td>${note.title}</td>
                    <td>${note.contents}</td>
                    <td>${note.owner.firstname}</td>
                    <td>
                        <form action="notes" method="post" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedUsername" value="${note.noteID}">
                        </form>
                    </td>
                    <td>
                        <form action="notes" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedUsername" value="${note.noteID}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${selectedUser == null}">
            <h3>Add Note</h3>
            <form action="notes" method="POST">
                note id: <input type="text" name="noteId" value="${selectedUser.noteID}"><br>
                title: <input type="text" name="title" value="${selectedUser.title}"><br>
                contents: <input type="text" name="contents" value="${selectedUser.contents}"><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>
        <c:if test="${selectedUser != null}">
            <h3>Edit Note</h3>
            <form action="notes" method="POST">
                note id: <input type="text" name="noteId" value="${selectedUser.noteID}" readonly><br>
                title: <input type="text" name="title" value="${selectedUser.title}"><br>
                contents: <input type="text" name="contents" value="${selectedUser.contents}"><br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
    </body>
</html>
