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
 * @author suxin 2017年11月24日 prjName:ServeletDemo pakName:com.httpservlet.demo
 *         隐藏域的书写测试 2017年11月24日
 */
public class HiddenFiledServlet extends HttpServlet {

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
		this.customers.add(customer1);

		Customer customer2 = new Customer();
		customer2.setId(2);
		customer2.setName("qinpeng");
		customer2.setCity("yangzhou1");
		this.customers.add(customer2);
	}

	private void sendCustomerList(HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><title>CustomerList</title></head>" + "<body><h2>Customers</h2>");
		writer.println("<ul>");
		for (Customer customer : customers) {
			writer.println("<li>" + customer.getName() + "(" + customer.getCity() + ")  (" + "<a href='editCustomer?id="
					+ customer.getId() + "'>edit</a>)");
		}
		writer.println("</ul>");
		writer.println("</body></html>");

	}

	private Customer getcutomer(int customerID) {
		for (Customer customer : customers) {
			if (customer.getId() == customerID) {
				return customer;
			}
		}
		return null;
	}

	// 暂停到此处
	private void sendEditCustomerForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		int customerID = 0;
		try {
			customerID = Integer.parseInt(req.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Customer customer = getcutomer(customerID);//取得客户对象
		if (customer != null) {
			writer.println("<html><head>"
					+ "<title>Edit Customer</title></head>"
					+ "<body><h2>Edit Customer</h2>"
					+ "<form method='post' action='updateCustomer'>");
			//此处将参数放在表单的隐藏域中，没有额外的编码，也没有字符限制，可以用于传递json
			writer.println("<input type='hidden' name='id' value='"+customerID+"'>");
			writer.println("<Table>");
			writer.println("<tr><td>Name:</td><td>"
					+ "<input name='name' value='"
					+ customer.getName().replaceAll("'", "&#39")
					+ "'/></td></tr>");
			writer.println("<tr><td>City:</td><td>"
					+ "<input name='city' value='"
					+ customer.getCity().replaceAll("'", "&#39")
					+ "'/></td></tr>");
			writer.println("<tr>"
					+ "<td colspan='2' style='text-align:right'>"
					+ "<input type='submit' value='Update'/></td>"
					+ "</tr>");
			writer.println("<tr><td colspan='2'>"
					+ "<a href='customer'>Customer List</a>"
					+ "</td></tr>");
			writer.println("</Table>");
			writer.println("</form></body>");
		} else {
			writer.println("No customer found");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//取得请求中的URI
		String uri = req.getRequestURI();
		if (uri.endsWith("/customer")) {
			sendCustomerList(resp);
		} else if(uri.endsWith("/editCustomer")) {
			sendEditCustomerForm(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int customerId = 0;
		try {
			customerId = Integer.parseInt(req.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Customer customer = getcutomer(customerId);
		if (customer != null) {
			customer.setName(req.getParameter("name"));
			customer.setCity(req.getParameter("city"));
		}
		sendCustomerList(resp);
	}

}
