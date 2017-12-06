<%@ tag language="java" pageEncoding="UTF-8"%>
<!-- invoke动作元素和dobody类似，用来调用fragment，
其有四个属性，调用fragment的名称，var或者varreader用于保存标签主体内的内容的变量值，scope表示变量作用范围 -->
<%@ attribute name="productDetails" fragment="true" %>
<%@ variable name-given="productName" %>
<%@ variable name-given="description" %>
<%@ variable name-given="price" %>
<%
    jspContext.setAttribute("productName", "Pelesonic DVD Player");
    jspContext.setAttribute("description", 
        "Dolby Digital output through coaxial digital-audio jack," + 
        " 500 lines horizontal resolution-image digest viewing");
    jspContext.setAttribute("price", "65");
%>
<jsp:invoke fragment="productDetails"/>
