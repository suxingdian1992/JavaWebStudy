<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Country List</title>
</head>
<body>
	We operate in these countries:
	<ul>
		<!-- 从servlet上下文中直接取得 再用el标准jstl标签库的便利操作进行遍历-->
		<c:forEach items="${countries}" var="country">
			<li>${country.value}</li>
		</c:forEach>
	</ul>
</body>
</html>