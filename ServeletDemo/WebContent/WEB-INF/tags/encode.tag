<%@ tag language="java" pageEncoding="UTF-8"%>
<!-- 定义属性名为input，相当于定义参数名，然后在文末将该属性传给tag内的方法 -->
<%@ attribute name="input" required="true" %>
<%!
    private String encodeHtmlTag(String tag) {
        if (tag==null) {
            return null;
        }
        int length = tag.length();
        StringBuilder encodedTag = new StringBuilder(2 * length);
        for (int i=0; i<length; i++) {
            char c = tag.charAt(i);
            if (c=='<') {
                encodedTag.append("&lt;");
            } else if (c=='>') {
                encodedTag.append("&gt;");
            } else if (c=='&') {
                encodedTag.append("&amp;");
            } else if (c=='"') {
                encodedTag.append("&quot;");  
            } else if (c==' ') {
                encodedTag.append("&nbsp;");
            } else {
                encodedTag.append(c);
            }
        }
        return encodedTag.toString();
    }
%>
<!-- 此处控制参数传入 -->
<%=encodeHtmlTag(input)%>