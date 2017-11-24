package com.servelet.demo;

// �������� java ��
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// ��չ HttpServlet ��
public class HelloWorld extends HttpServlet {
 
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String message;

  public void init() throws ServletException
  {
      // ִ�б���ĳ�ʼ��
      message = "Hello World";
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // ������Ӧ��������
      response.setContentType("text/html");

      // ʵ�ʵ��߼���������
      PrintWriter out = response.getWriter();
      out.println("<h1>" + message + "</h1>");
  }
  
  public void destroy()
  {
      // ʲôҲ����
  }
}