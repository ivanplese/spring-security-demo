<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Overview</title>
</head>
<body>

<h1>Messages Page</h1>

<hr/>
<p>
    <span style="font-weight:bold">Current user: </span>${username}<br/>
    <span style="font-weight:bold">Current role: </span>${role}
</p>

<div style="width: 600px" >
    <div>
        <table style="border: 1px solid; width: 500px; text-align:center">
            <caption>Public Posts</caption>
            <thead style="background:#fcf">
            <tr>
                <th>Date</th>
                <th>Message</th>
                <th colspan="3"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${publicmessages}" var="message">
                <c:url var="editUrl" value="/public/edit?id=${message.id}" />
                <c:url var="deleteUrl" value="/public/delete?id=${message.id}" />
                <c:url var="addUrl" value="/public/add" />
                <tr>
                    <td><c:out value="${message.date}" /></td>
                    <td><c:out value="${message.text}" /></td>
                    <td><a href="${editUrl}">Edit</a></td>
                    <td><a href="${deleteUrl}">Delete</a></td>
                    <td><a href="${addUrl}">Add</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <hr/>
</div>
<a href="/auth/logout">Log out</a>
</body>
</html>