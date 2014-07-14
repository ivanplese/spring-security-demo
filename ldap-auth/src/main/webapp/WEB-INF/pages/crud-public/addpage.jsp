<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Create New ${source} Post</h1>
<p>
    <span style="font-weight:bold">Current user: </span>${username}<br/>
    <span style="font-weight:bold">Current role: </span>${role}
</p>

<c:url var="saveUrl" value="/public/add" />
<form:form modelAttribute="messageAttribute" method="POST" action="${saveUrl}">
	<table>
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