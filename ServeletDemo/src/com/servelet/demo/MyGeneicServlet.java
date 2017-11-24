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

/*此处标注用来声明这是一个servlet，使用什么url进入到这个servlet中*/
/**
 * @author suxin
 * 2017年11月22日
 * prjName:ServeletDemo pakName:com.servelet.demo
 * GenericServlet的实际应用
 * GenericServlet提供了一下东西
 * 1、将init方法中的servletconfig赋值给一个类级变量，以便可以由getServletConfig方法取得
 * 2、为servlet接口中的所有方法提供了默认实现
 * 3、提供方法，包围servletconfig中的方法
 * 2017年11月22日
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
		ServletConfig servletConfig = this.getServletConfig();//取得当前的注解配置
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
