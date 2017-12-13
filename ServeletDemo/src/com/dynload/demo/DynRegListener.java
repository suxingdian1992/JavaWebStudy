package com.dynload.demo;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

/**
 * @author suxin
 * 2017��12��13��
 * prjName:ServeletDemo pakName:com.dynload.demo
 * TODO
 * �˴�ʹ��servletcontext�Ľӿ���ʵ��ͬ�ļ�����firstservlet��ע����Ч
 * 2017��12��13��
 */
@WebListener
public class DynRegListener implements ServletContextListener {

	 @Override
	    public void contextDestroyed(ServletContextEvent sce) {
	    }

	    // use createServlet to obtain a Servlet instance that can be
	    // configured prior to being added to ServletContext
	    @Override
	    public void contextInitialized(ServletContextEvent sce) {
	        ServletContext servletContext = sce.getServletContext();//ȡ��servletcontext���󣬼���ȡservlet�������ģ���������ע��servlet��listener��filter
	        
	        Servlet firstServlet = null;
	        try {
	            firstServlet = 
	                servletContext.createServlet(FirstServlet.class);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        if (firstServlet != null && firstServlet instanceof 
	                FirstServlet) {
	            ((FirstServlet) firstServlet).setName(
	                    "Dynamically registered servlet");
	        }
	        
	        // the servlet may not be annotated with @WebServletһ�ϲ��贴�����servlet֮����ҪΪ��ע�ᣬ�����뵽��������
	        ServletRegistration.Dynamic dynamic = servletContext.
	                addServlet("firstServlet", firstServlet);
	        dynamic.addMapping("/dynamic");
	    }
    
}