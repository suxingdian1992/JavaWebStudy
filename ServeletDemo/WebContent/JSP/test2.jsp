<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<html>
<head>
<title>Form Values</title>
</head>
<body>
<table>
    <tr>
        <td>Name:</td>
        <td>
            ${param.name} 
            (length:${fn:length(param.name)})
        </td>
    </tr>
    <tr>
        <td>Address:</td>
        <td>
            ${param.address} 
            (length:${fn:length(param.address)})
        </td>
    </tr>
</table>
</body>
</html>