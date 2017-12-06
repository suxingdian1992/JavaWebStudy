<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag import="java.util.Date" import="java.text.DateFormat"%>
<%
	/* tag file使用，如何为jsp页面创建tag文件 */
	DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
	Date now = new Date(System.currentTimeMillis());
	out.println(dateFormat.format(now));
%>
