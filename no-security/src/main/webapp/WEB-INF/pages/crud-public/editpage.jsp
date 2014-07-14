<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit public message</title>
</head>
<body>

<h1>Edit ${source} Post</h1>

<c:url var="saveUrl" value="/public/edit?id=${messageAttribute.id}" />
<form:form modelAttribute="messageAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="id">Id:</form:label></td>
            <td><form:input path="id" disabled="true"/></td>
        </tr>

        <tr>
            <td><form:label path="date">Date:</form:label></td>
            <td><form:input path="date" disabled="true"/></td>
        </tr>

        <tr>
            <td><form:label path="text">Message:</form:label></td>
            <td><form:input path="text"/></td>
        </tr>
    </table>

    <input type="submit" value="Save" />
</form:form>
<a href="/messages/overview">Go to overview</a>
</body>
</html>