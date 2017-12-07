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
 * 2017年12月7日
 * prjName:ServeletDemo pakName:com.filter.demo
 * 过滤器实例
 * 2017年12月7日
 */
@WebFilter(filterName = "LoggingFilter", urlPatterns = { "/*" }, /*注意，此处配置的url拦截标记表示所有请求都拦截*/
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
		//从初始化参数中取得prefix，logFileName
		prefix = filterConfig.getInitParameter("prefix");
		String logFileName = filterConfig.getInitParameter("logFileName");
		//获取当前应用的部署位置，该位置中可以找到日志文件
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
		//向日志文件中写入请求的地址和请求时间，每次访问都会被过滤器拦截下当前配置的连接
		logger.println(new Date() + " " + prefix + httpServletRequest.getRequestURI());
		System.out.println(new Date() + " " + prefix + httpServletRequest.getRequestURI());
		logger.flush();
		filterChain.doFilter(request, response);
	}
}