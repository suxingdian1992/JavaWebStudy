package com.async.demo;

import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AsyncDispatchServlet", urlPatterns = { "/asyncDispatch" }, asyncSupported = true)
public class AsyncDispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 222L;

	@Override
	public void doGet(final HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final AsyncContext asyncContext = request.startAsync();
		//�������̵߳��߳�����
		request.setAttribute("mainThread", Thread.currentThread().getName());
		asyncContext.setTimeout(5000);
		asyncContext.start(new Runnable() {
			@Override
			public void run() {
				/*��ʱ����������*/
				// long-running task
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}
				//�����첽�̵߳��߳�����,�˴�Ӧ�������̲߳���һ���߳�
				request.setAttribute("workerThread", Thread.currentThread().getName());
				//dispatch�������������󵽸�λ��
				asyncContext.dispatch("/JSP/threadNames.jsp");
			}
		});
	}
}