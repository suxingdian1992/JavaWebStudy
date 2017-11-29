package com.httpservlet.demo.httpsession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author suxin
 * 2017年11月28日
 * prjName:ServeletDemo pakName:com.httpservlet.demo.httpsession
 * 1、所有保存在HttpSession的数据都不会被发送感到客户端
 * 2、每个servlet容器为每个httpsession生成唯一标识，并讲该标识发送到浏览器，或者创建一个名为jsessionid的cookie，
 * 或者在URL后面追加一个名为jsessionid的参数
 * 3、要获取httpsession的唯一表示，可以调用httpsession的getId方法来读取该标识
 * 4、httpseeion中有一个方法叫做invalidate的方法，此方法强制当前session过期
 * 5、默认情况下，httpsession在用户不活动一段时间之后过期，过期的时间在部署描述符中可以用session-timeout标签设定，比如30，表示30分
 * 6、大部分情况下需要主动失效不用的httpsession，释放内存
 * 2017年11月28日
 */
/**
 * @author suxin
 * 2017年11月28日
 * prjName:ServeletDemo pakName:com.httpservlet.demo.httpsession
 * TODO
 * 2017年11月28日
 */
@WebServlet(name = "ShoppingCartServlet", urlPatterns = {
        "/products", "/viewProductDetails", 
        "/addToCart", "/viewCart","/clearCart" })
public class ShoppingCartServlet extends HttpServlet {
    private static final long serialVersionUID = -20L;
    private static final String CART_ATTRIBUTE = "cart";

    private List<Product> products = new ArrayList<Product>();
    private NumberFormat currencyFormat = NumberFormat
            .getCurrencyInstance(Locale.US);

    @Override
    public void init() throws ServletException {
        products.add(new Product(1, "Bravo 32' HDTV",
                "Low-cost HDTV from renowned TV manufacturer",
                159.95F));
        products.add(new Product(2, "Bravo BluRay Player",
                "High quality stylish BluRay player", 99.95F));
        products.add(new Product(3, "Bravo Stereo System",
                "5 speaker hifi system with iPod player", 
                129.95F));
        products.add(new Product(4, "Bravo iPod player",
                "An iPod plug-in that can play multiple formats",
                39.95F));
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
    	//注意URL和URI的区别：URL是一种具体的URI，它不仅唯一标识资源，而且还提供了定位该资源的信息。
    	//URI是一种语义上的抽象概念，可以是绝对的，也可以是相对的，而URL则必须提供足够的信息来定位，
    	//所以，是绝对的，而通常说的relative URL，则是针对另一个absolute URL，本质上还是绝对的。
        String uri = request.getRequestURI();
        if (uri.endsWith("/products")) {
        	//展示当前所有产品的清单
            sendProductList(response);
        } else if (uri.endsWith("/viewProductDetails")) {
        	//展示产品详情信息
            sendProductDetails(request, response);
        } else if (uri.endsWith("viewCart")) {
        	//展示购物车中的商品
            showCart(request, response);
        } else if (uri.endsWith("/clearCart")) {
        	//清空购物车
        	clearCart(request, response);
			
		}
    }

    private void clearCart(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
    	HttpSession session = request.getSession();
        session.setAttribute(CART_ATTRIBUTE, null);//刷新session中的购物车对象
        try {
        	//清空购物车回到商品列表
			sendProductList(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * 向购物车添加商品
     */
    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        // add to cart 向购物车中添加信息，在产品详情的表单中有post入口
        int productId = 0;
        int quantity = 0;
        try {
            productId = Integer.parseInt(
                    request.getParameter("id"));
            quantity = Integer.parseInt(request
                    .getParameter("quantity"));
        } catch (NumberFormatException e) {
        }

        Product product = getProduct(productId);
        if (product != null && quantity >= 0) {
            ShoppingItem shoppingItem = new ShoppingItem(product,
                    quantity);
            HttpSession session = request.getSession();
            List<ShoppingItem> cart = (List<ShoppingItem>) session
                    .getAttribute(CART_ATTRIBUTE);//获取session中的cart变量，类型是List<ShoppingItem>
            if (cart == null) {
                cart = new ArrayList<ShoppingItem>();
                session.setAttribute(CART_ATTRIBUTE, cart);//刷新session中的购物车对象
            }
            cart.add(shoppingItem);//向购物车中加入对象
        }
        sendProductList(response);
    }

    /**
     * 下午2:29:20
     * @param response
     * @throws IOException
     * void
     * 展示产品清单
     * suxin
     */
    private void sendProductList(HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>Products</title>" +
        		"</head><body><h2>Products</h2>");
        writer.println("<ul>");
        for (Product product : products) {
            writer.println("<li>" + product.getName() + "("
                    + currencyFormat.format(product.getPrice())
                    + ") (" + "<a href='viewProductDetails?id="
                    + product.getId() + "'>Details</a>)");
        }
        writer.println("</ul>");
        writer.println("<a href='viewCart'>View Cart</a>");
        writer.println("</body></html>");

    }

    private Product getProduct(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    private void sendProductDetails(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        int productId = 0;
        try {
            productId = Integer.parseInt(
                    request.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        Product product = getProduct(productId);

        if (product != null) {
            writer.println("<html><head>"
                    + "<title>Product Details</title></head>"
                    + "<body><h2>Product Details</h2>"
                    + "<form method='post' action='addToCart'>");
            writer.println("<input type='hidden' name='id' "
            		+ "value='" + productId + "'/>");
            writer.println("<table>");
            writer.println("<tr><td>Name:</td><td>"
                    + product.getName() + "</td></tr>");
            writer.println("<tr><td>Description:</td><td>"
                    + product.getDescription() + "</td></tr>");
            writer.println("<tr>" + "<tr>"
                    + "<td><input name='quantity'/></td>"
                    + "<td><input type='submit' value='Buy'/>"
                    + "</td>"
                    + "</tr>");
            writer.println("<tr><td colspan='2'>"
                    + "<a href='products'>Product List</a>"
                    + "</td></tr>");
            writer.println("</table>");
            writer.println("</form></body>");
        } else {
            writer.println("No product found");
        }

    }

    /**
     * 下午2:31:11
     * @param request
     * @param response
     * @throws IOException
     * void
     * 展示购物车里的项目
     * suxin
     */
    private void showCart(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>Shopping Cart</title>"
        		+ "</head>");
        writer.println("<body><a href='products'>" +
        		"Product List</a>");
        writer.println("<a href='clearCart'>" +
		"Clear Cart</a>");
        HttpSession session = request.getSession();
        List<ShoppingItem> cart = (List<ShoppingItem>) session
                .getAttribute(CART_ATTRIBUTE);
        if (cart != null) {
            writer.println("<table>");
            writer.println("<tr><td style='width:150px'>Quantity"
            		+ "</td>"
                    + "<td style='width:150px'>Product</td>"
                    + "<td style='width:150px'>Price</td>"
                    + "<td>Amount</td></tr>");
            double total = 0.0;
            for (ShoppingItem shoppingItem : cart) {
                Product product = shoppingItem.getProduct();
                int quantity = shoppingItem.getQuantity();
                if (quantity != 0) {
                    float price = product.getPrice();
                    writer.println("<tr>");
                    writer.println("<td>" + quantity + "</td>");
                    writer.println("<td>" + product.getName()
                            + "</td>");
                    writer.println("<td>"
                            + currencyFormat.format(price) 
                            + "</td>");
                    double subtotal = price * quantity;

                    writer.println("<td>"
                            + currencyFormat.format(subtotal)
                            + "</td>");
                    total += subtotal;
                    writer.println("</tr>");
                }
            }
            writer.println("<tr><td colspan='4' " 
                    + "style='text-align:right'>"
                    + "Total:"
                    + currencyFormat.format(total)
                    + "</td></tr>");
            writer.println("</table>");
        }
        writer.println("</table></body></html>");

    }
}