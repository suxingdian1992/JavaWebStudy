package com.dynload.demo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suxin
 * 2017年12月13日
 * prjName:ServeletDemo pakName:com.dynload.demo
 * TODO
 * 本servlet为在部署描述符中标注，也没有@webservlet注解，使用一个listener来启动这个servlet
 * 2017年12月13日
 */
public class FirstServlet extends HttpServlet {
    private static final long serialVersionUID = -6045338L;

    private String name;
    
    @Override
    public void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>First servlet" +
                "</title></head><body>" + name);
        writer.println("</body></head>");
    }   
    
    public void setName(String name) {
    	//有监听器在初始化的时候动态为其设置值
        this.name = name;
    }
}