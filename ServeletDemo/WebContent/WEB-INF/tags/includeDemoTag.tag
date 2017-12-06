注意在文件头加入tag文件的编码格式
<br/>
<%@ tag pageEncoding="utf-8" %>
This tag file shows the use of the include directive. 
The first include directive demonstrates how you can include
a static resource called included.html.
<br/>
Here is the content of included.html:
<br/>
引入外部html文件
<%@ include file="included.html" %>
<br/>
<br/>
The second include directive includes another dynamic resource: 
included.tagf.
<br/>

引入外部标签文件片段
<br/>
<%@ include file="included.tagf" %>