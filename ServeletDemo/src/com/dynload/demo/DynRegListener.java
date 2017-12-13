package com.dynload.demo;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

/**
 * @author suxin
 * 2017年12月13日
 * prjName:ServeletDemo pakName:com.dynload.demo
 * TODO
 * 此处使用servletcontext的接口来实现同文件夹下firstservlet的注册生效
 * 2017年12月13日
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
	        ServletContext servletContext = sce.getServletContext();//取得servletcontext对象，即获取servlet的上下文，允许创建并注册servlet，listener，filter
	        
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
	        
	        // the servlet may not be annotated with @WebServlet一上步骤创建完毕servlet之后还需要为其注册，并加入到上下文中
	        ServletRegistration.Dynamic dynamic = servletContext.
	                addServlet("firstServlet", firstServlet);
	        dynamic.addMapping("/dynamic");
	    }
    
}