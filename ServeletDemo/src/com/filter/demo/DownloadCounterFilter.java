package com.filter.demo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "DownloadCounterFilter", urlPatterns = { "/*" })
public class DownloadCounterFilter implements Filter {

	ExecutorService executorService = Executors.newSingleThreadExecutor();
	Properties downloadLog;
	File logFile;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("DownloadCounterFilter");
		// 从上下问取得当前的服务器地址
		String appPath = filterConfig.getServletContext().getRealPath("/");
		// 在服务器地址下创建文件：downloadLog.txt
		logFile = new File(appPath, "downloadLog.txt");
		if (!logFile.exists()) {
			try {// 如果当前文件不存在，则创建该文件
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		downloadLog = new Properties();
		try {
			// 文件装载
			downloadLog.load(new FileReader(logFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		//注意，当filter结束的时候会掉用executorService的关闭方法，关闭文件读取
		executorService.shutdown();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		// 从请求中取得uri
		final String uri = httpServletRequest.getRequestURI();
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				String property = downloadLog.getProperty(uri);
				if (property == null) {// 如果是空的则下载次数为1
					downloadLog.setProperty(uri, "1");// 设定属性，属性名为uri，值为数字
					System.out.println("DownloadCounter:"+uri+" Counts:1");
				} else {
					int count = 0;
					try {
						count = Integer.parseInt(property);
					} catch (NumberFormatException e) {
						// silent
					}
					count++;
					System.out.println("DownloadCounter:"+uri+" Counts:"+Integer.toString(count));
					//在文件中找到对应的属性然后修改，不是重新插入，注意
					downloadLog.setProperty(uri, Integer.toString(count));
				}
				try {
					//向文件中写入日志
					downloadLog.store(new FileWriter(logFile), "");
				} catch (IOException e) {
				}
			}
		});
		filterChain.doFilter(request, response);
	}
}