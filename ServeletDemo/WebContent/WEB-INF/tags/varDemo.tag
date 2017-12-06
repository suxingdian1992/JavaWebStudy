<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag import="java.util.Date" import="java.text.DateFormat"%>
<%@ variable name-given="longDate" %>
<%@ variable name-given="shortDate" %>
<%
    Date now = new Date(System.currentTimeMillis());
    DateFormat longFormat =
            DateFormat.getDateInstance(DateFormat.LONG);
    DateFormat shortFormat = 
            DateFormat.getDateInstance(DateFormat.SHORT);
    //引用标签之后将参数设定给jspcontext中，上下文对象中设定完成后可在jsp页面上由el表达式直接取用
    jspContext.setAttribute("longDate", longFormat.format(now));
    jspContext.setAttribute("shortDate", shortFormat.format(now));
%>
<jsp:doBody/>