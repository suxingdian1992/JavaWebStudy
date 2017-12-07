package com.async.demo;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AsyncListenerServlet", urlPatterns = { "/asyncListener" }, asyncSupported = true)
public class AsyncListenerServlet extends HttpServlet {
	private static final long serialVersionUID = 62738L;

	@Override
	public void doGet(final HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final AsyncContext asyncContext = request.startAsync();
		asyncContext.setTimeout(5000);
		// 通過調用该方法为监听器注册，异步监听器（implements AsyncListener）这种形式的不需要单独描述或者使用注解说明注册，
		asyncContext.addListener(new MyAsyncListener());
		asyncContext.start(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				String greeting = "hi from listener";
				System.out.println("wait....");
				request.setAttribute("greeting", greeting);
				asyncContext.dispatch("/JSP/testAsync.jsp");
			}
		});
	}
}