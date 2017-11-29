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
 * 2017年11月22日
 * prjName:ServeletDemo pakName:com.servelet.demo
 * 标准servlet接口实现以及样例！
 * 注意url和uri的区别
 * 2017年11月22日
 */
/*此处标注用来声明这是一个servlet，使用什么url进入到这个servlet中*/
@WebServlet(name = "MyServlet"
, urlPatterns = {"/my"}
, initParams = {
	@WebInitParam(name = "admin", value = "suxingdian"),
	@WebInitParam(name = "email", value = "suxingdian@live.com")
})
public class MyServlet implements Servlet{

	private transient ServletConfig servletConfig;
	
	/*servlet默认提供的接口方法*/
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
		//将servlet的记本配置信息赋值给this.servletConfig
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
