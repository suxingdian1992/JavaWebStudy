package com.servelet.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;


/**
 * @author suxin
 * 2017��11��22��
 * prjName:ServeletDemo pakName:com.servelet.demo
 * ��׼servlet�ӿ�ʵ���Լ�������
 * ע��url��uri������
 * 2017��11��22��
 */
/*�˴���ע������������һ��servlet��ʹ��ʲôurl���뵽���servlet��*/
@WebServlet(name = "MyServlet"
, urlPatterns = {"/my"}
, initParams = {
	@WebInitParam(name = "admin", value = "suxingdian"),
	@WebInitParam(name = "email", value = "suxingdian@live.com")
})
public class MyServlet implements Servlet{

	private transient ServletConfig servletConfig;
	
	/*servletĬ���ṩ�Ľӿڷ���*/
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return servletConfig;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return "Hello World!";
	}

	@Override
	public void init(ServletConfig servletconfig) throws ServletException {
		// TODO Auto-generated method stub
		//��servlet�ļǱ�������Ϣ��ֵ��this.servletConfig
		this.servletConfig = servletconfig;
	}

	@Override
	public void service(ServletRequest servletrequest, ServletResponse servletresponse)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String servletName = servletConfig.getServletName();
		String admin = servletConfig.getInitParameter("admin");
		String email = servletConfig.getInitParameter("email");
		servletresponse.setContentType("text/html");
		PrintWriter writer = servletresponse.getWriter();
		writer.print("<html><head></head>"
				+ "<body>Hello Form " + servletName 
				+ "<br/> Admin: " + admin
				+ "<br/> Email: " + email
				+ "</body></html>");
		
	}

}
