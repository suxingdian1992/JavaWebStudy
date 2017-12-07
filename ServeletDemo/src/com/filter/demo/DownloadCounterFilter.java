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
		// ��������ȡ�õ�ǰ�ķ�������ַ
		String appPath = filterConfig.getServletContext().getRealPath("/");
		// �ڷ�������ַ�´����ļ���downloadLog.txt
		logFile = new File(appPath, "downloadLog.txt");
		if (!logFile.exists()) {
			try {// �����ǰ�ļ������ڣ��򴴽����ļ�
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		downloadLog = new Properties();
		try {
			// �ļ�װ��
			downloadLog.load(new FileReader(logFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		//ע�⣬��filter������ʱ������executorService�Ĺرշ������ر��ļ���ȡ
		executorService.shutdown();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		// ��������ȡ��uri
		final String uri = httpServletRequest.getRequestURI();
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				String property = downloadLog.getProperty(uri);
				if (property == null) {// ����ǿյ������ش���Ϊ1
					downloadLog.setProperty(uri, "1");// �趨���ԣ�������Ϊuri��ֵΪ����
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
					//���ļ����ҵ���Ӧ������Ȼ���޸ģ��������²��룬ע��
					downloadLog.setProperty(uri, Integer.toString(count));
				}
				try {
					//���ļ���д����־
					downloadLog.store(new FileWriter(logFile), "");
				} catch (IOException e) {
				}
			}
		});
		filterChain.doFilter(request, response);
	}
}