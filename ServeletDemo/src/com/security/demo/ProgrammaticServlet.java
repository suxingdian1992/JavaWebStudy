package com.security.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/prog" })
public class ProgrammaticServlet extends HttpServlet {

	private static final long serialVersionUID = 87620L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*tomcat允许使用jdbc和数据库中的用户名密码进行匹配，此处需要注意*/
		if (request.authenticate(response)) {//此处指定使用验证，如果验证通过则继续，否则不继续，验证的配置文件从tomcat-user.xml中取得
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("Welcome");
		} else {
			// user not authenticated
			// do something
			System.out.println("User not authenticated");
		}
	}
}