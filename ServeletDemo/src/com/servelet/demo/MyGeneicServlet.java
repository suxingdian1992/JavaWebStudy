package com.servelet.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/*�˴���ע������������һ��servlet��ʹ��ʲôurl���뵽���servlet��*/
/**
 * @author suxin
 * 2017��11��22��
 * prjName:ServeletDemo pakName:com.servelet.demo
 * GenericServlet��ʵ��Ӧ��
 * GenericServlet�ṩ��һ�¶���
 * 1����init�����е�servletconfig��ֵ��һ���༶�������Ա������getServletConfig����ȡ��
 * 2��Ϊservlet�ӿ��е����з����ṩ��Ĭ��ʵ��
 * 3���ṩ��������Χservletconfig�еķ���
 * 2017��11��22��
 */
@WebServlet(name = "MyGeneicServlet"
, urlPatterns = {"/myge"}
, initParams = {
	@WebInitParam(name = "admin", value = "suxingdian"),
	@WebInitParam(name = "email", value = "suxingdian@live.com")
})
public class MyGeneicServlet extends GenericServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7818334470341482492L;

	@Override
	public void service(ServletRequest servletrequest, ServletResponse servletresponse)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletConfig servletConfig = this.getServletConfig();//ȡ�õ�ǰ��ע������
		String servletName = servletConfig.getServletName();
		String admin = servletConfig.getInitParameter("admin");
		String email = servletConfig.getInitParameter("email");
		servletresponse.setContentType("text/html");
		PrintWriter writer = servletresponse.getWriter();
		writer.print("<html><head></head>"
				+ "<body>GenericServlet " + servletName 
				+ "<br/> Admin: " + admin
				+ "<br/> Email: " + email
				+ "</body></html>");
		
	}

}
