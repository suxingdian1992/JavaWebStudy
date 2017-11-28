package com.httpservlet.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suxin
 * 2017年11月23日
 * prjName:ServeletDemo pakName:com.httpservlet.demo
 * Top10Servlet，测试使用token传递参数
 * 2017年11月23日
 */
public class Top10Servlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> londonAttractions;
	private List<String> parisAttractions;
	
	@Override
	public void init() throws ServletException {
		londonAttractions = new ArrayList<String>(10);
		londonAttractions.add("lodon1");
		londonAttractions.add("lodon2");
		londonAttractions.add("lodon3");
		londonAttractions.add("lodon4");
		londonAttractions.add("lodon5");
		londonAttractions.add("lodon6");
		londonAttractions.add("lodon7");
		londonAttractions.add("lodon8");
		londonAttractions.add("lodon9");
		londonAttractions.add("lodon10");
		
		parisAttractions = new ArrayList<String>(10);
		parisAttractions.add("paris1");
		parisAttractions.add("paris2");
		parisAttractions.add("paris3");
		parisAttractions.add("paris4");
		parisAttractions.add("paris5");
		parisAttractions.add("paris6");
		parisAttractions.add("paris7");
		parisAttractions.add("paris8");
		parisAttractions.add("paris9");
		parisAttractions.add("paris10");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String city = req.getParameter("city");
		if (city != null && (city.equals("london") || city.equals("paris"))) {
			//show attractions
			showAttractions(req,resp);
		} else {
			//show main page
			showMainPage(req,resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	/**
	 * 重写url
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showMainPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//重写url动态修正链接参数，在url后追加token参数，用？开始，&分割
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.print("<html><head>" + 
		"<title>Top 10 Tourist Attractions</title>"+
		"</head><body>"+
		"please select a city:"+
		"<br/><a href='?city=london'>London</a>"+
		"<br/><a href='?city=paris'>Paris</a>"+
		"</body></html>");
	}
	
	private void showAttractions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		int page = 1;
		String pageParameter = req.getParameter("page");
		if(pageParameter != null) {
			try {
				page = Integer.parseInt(pageParameter);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(page > 2) {
				page = 1;
			}
		}
		List<String> attractions = null;
		String city = req.getParameter("city");
		if(city.equals("london")) {
			attractions = this.londonAttractions;
		} else if(city.equals("paris")) {
			attractions = this.parisAttractions;
		} else {
			
		}
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head>"
				+ "<title>Top 10 Tourist Attractions</title>"
				+ "</head><body>"
				+ "<br/><a href='top10'>Select City</a>"
				);
		writer.println("<hr/>Pagr "+ page + "<hr />");
		int start = page * 5 - 5;
		for (int i = start; i < start+5; i++) {
			writer.println(attractions.get(i) + "<br />");
		}
		//注意，在使用url重写传递参数的时候不能有空格，否则会不是别
		writer.print("<hr style = 'color:blue'/>"
				+ "<a href='?city="+city
				+ "&page=1'>page 1 </a>");
		writer.println("&nbsp; <a href='?city="+city
				+ "&page=2'>page 2 </a>");
		writer.println("</body></html>");
	}
	

}
