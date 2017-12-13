package com.servletcontainer.demo;

import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;

import com.dynload.demo.FirstServlet;
import com.servletcontainer.demo.UsefulServlet;

/**
 * @author suxin
 * 2017年12月13日
 * prjName:ServeletDemo pakName:com.servletcontainer.demo
 * TODO
 * 所有的servlet启动的时候都会进行该方法，onStartup,这就是容器加载器，需要在前面加上注解：@HandlesTypes({ UsefulServlet.class })以便让加载器能够识别
 * 2017年12月13日
 */
@HandlesTypes({ UsefulServlet.class })
public class MyServletContainerInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {
		Servlet usefulServlet = null;
		try {
			usefulServlet = servletContext.createServlet(FirstServlet.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (usefulServlet != null && usefulServlet instanceof UsefulServlet) {
			((FirstServlet) usefulServlet).setName("ServletContainerOnStartUp");
		}
		
		System.out.println("onStartup");
		ServletRegistration registration = servletContext.addServlet("usefulServlet",
				"com.servletcontainer.demo.UsefulServlet");// 注册当前的servlet类
		registration.addMapping("/useful");
		System.out.println("leaving onStartup");
		
	}
}