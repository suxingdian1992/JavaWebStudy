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
 * 2017年11月24日
 * prjName:ServeletDemo pakName:com.httpservlet.demo
 * 隐藏域的书写测试
 * 2017年11月24日
 */
public class HiddenFiledServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Customer> customers = new ArrayList<Customer>();
	
	@Override
	public void init() throws ServletException {
		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setName("Suxingdian");
		customer1.setCity("chengdu");
		
		Customer customer2 = new Customer();
		customer2.setId(2);
		customer2.setName("qinpeng");
		customer2.setCity("yangzhou1");
	}
	
	private void sendCustomerList(HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><title>CustomerList</title></head>"
				+ "<body><h2>Customers</h2>");
		writer.println("<ul>");
		for (Customer customer : customers) {
			writer.println("<li>"+customer.getName()
			+"("+customer.getCity()+")  ("
			+"<a herf='editCustomer?id="+customer.getId()
			+"'>edit</a>");
		}
		writer.println("</ul>");
		writer.println("</body></html>");
		
	}
	
	private Customer getcutomer(int customerID) {
		for (Customer customer : customers) {
			if (customer.getId()==customerID) {
				return customer;
			}
		}
		return null;
	}
	//暂停到此处
	private void sendCustomerForm() {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}


	
	
	
}
