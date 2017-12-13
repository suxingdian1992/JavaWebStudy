package com.dynload.demo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suxin
 * 2017��12��13��
 * prjName:ServeletDemo pakName:com.dynload.demo
 * TODO
 * ��servletΪ�ڲ����������б�ע��Ҳû��@webservletע�⣬ʹ��һ��listener���������servlet
 * 2017��12��13��
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
    	//�м������ڳ�ʼ����ʱ��̬Ϊ������ֵ
        this.name = name;
    }
}