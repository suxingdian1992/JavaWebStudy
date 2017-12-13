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
 * 2017��12��13��
 * prjName:ServeletDemo pakName:com.servletcontainer.demo
 * TODO
 * ���е�servlet������ʱ�򶼻���и÷�����onStartup,�������������������Ҫ��ǰ�����ע�⣺@HandlesTypes({ UsefulServlet.class })�Ա��ü������ܹ�ʶ��
 * 2017��12��13��
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
				"com.servletcontainer.demo.UsefulServlet");// ע�ᵱǰ��servlet��
		registration.addMapping("/useful");
		System.out.println("leaving onStartup");
		
	}
}