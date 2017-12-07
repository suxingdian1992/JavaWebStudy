package com.filter.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

/**
 * @author suxin
 * 2017��12��7��
 * prjName:ServeletDemo pakName:com.filter.demo
 * ������ʵ��
 * 2017��12��7��
 */
@WebFilter(filterName = "LoggingFilter", urlPatterns = { "/*" }, /*ע�⣬�˴����õ�url���ر�Ǳ�ʾ������������*/
	initParams = {
		@WebInitParam(name = "logFileName", value = "log.txt"),
		@WebInitParam(name = "prefix", value = "URI: ") 
		}
)
public class LoggingFilter implements Filter {

	private PrintWriter logger;
	private String prefix;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//�ӳ�ʼ��������ȡ��prefix��logFileName
		prefix = filterConfig.getInitParameter("prefix");
		String logFileName = filterConfig.getInitParameter("logFileName");
		//��ȡ��ǰӦ�õĲ���λ�ã���λ���п����ҵ���־�ļ�
		String appPath = filterConfig.getServletContext().getRealPath("/");
		// without path info in logFileName, the log file will be
		// created in $TOMCAT_HOME/bin

		System.out.println("logFileName:" + logFileName);
		try {
			logger = new PrintWriter(new File(appPath, logFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}

	@Override
	public void destroy() {
		System.out.println("destroying filter");
		if (logger != null) {
			logger.close();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("LoggingFilter.doFilter");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		//����־�ļ���д������ĵ�ַ������ʱ�䣬ÿ�η��ʶ��ᱻ�����������µ�ǰ���õ�����
		logger.println(new Date() + " " + prefix + httpServletRequest.getRequestURI());
		System.out.println(new Date() + " " + prefix + httpServletRequest.getRequestURI());
		logger.flush();
		filterChain.doFilter(request, response);
	}
}