<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 约定为自定义标签注册文件中约定的uri即可 -->
<%@ taglib uri="http://example.com/taglib/function" prefix="f"%>
<html>
<head>
    <title>Testing reverseString function</title>
</head>
<body>
${f:reverseString("Welcome")}
</body>
</html>