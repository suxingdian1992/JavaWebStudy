<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Book List</title>
<style>
table, tr, td {
	border: 1px solid brown;
}
</style>
</head>
<body>
	Books in Simple Table
	<table>
		<tr>
			<td>ISBN</td>
			<td>Title</td>
		</tr>
		<c:forEach items="${requestScope.books}" var="book">
			<tr>
				<td>${book.isbn}</td>
				<td>${book.title}</td>
			</tr>
		</c:forEach>
	</table>
	<br /> Books in Styled Table
	<table>
		<tr style="background: #ababff">
			<td>ISBN</td>
			<td>Title</td>
			<td>PRICE</td>
			<td>count</td>
		</tr>
		<!-- el表达式获取内置对象，前后太交互记本分离 -->
		<c:forEach items="${requestScope.books}" var="book" varStatus="status">
			<c:if test="${status.count%2 == 0}">
				<tr style="background: #eeeeff">
			</c:if>
			<c:if test="${status.count%2 != 0}">
				<tr style="background: #dedeff">
			</c:if>
			<td>${book.isbn}</td>
			<td>${book.title}</td>
			<td>${book.price}</td>
			<td>${status.count}</td>
			</tr>
		</c:forEach>
	</table>

	<br /> ISBNs only:
	<c:forEach items="${requestScope.books}" var="book" varStatus="status">
        ${book.isbn}<c:if test="${!status.last}">,</c:if>
	</c:forEach>
</body>
</html>
