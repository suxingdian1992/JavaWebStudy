package com.httpservlet.demo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suxin
 * 2017年11月27日
 * prjName:ServeletDemo pakName:com.httpservlet.demo
 * 1、同一应用中由cookie传递当前页面详情的相关参数，都在cookietest类中统一设定
 * 2017年11月27日
 */
@WebServlet(name = "CookieInfoServlet", urlPatterns = { "/cookieInfo" })
public class CookieInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 3829L;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

    	//从请求中取得之前的cookie数组，然后找到对应的cookie即可
        Cookie[] cookies = request.getCookies();
        StringBuilder styles = new StringBuilder();
        styles.append(".title {");
        if (cookies != null) {
        	//遍历cookie
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                if (name.equals("titleFontSize")) {
                    styles.append("font-size:" + value + ";");
                } else if (name.equals("titleFontWeight")) {
                    styles.append("font-weight:" + value + ";");
                } else if (name.equals("titleFontStyle")) {
                    styles.append("font-style:" + value + ";");
                }
            }
        }
        styles.append("}");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.print("<html><head>" + "<title>Cookie Info</title>"
                + "<style>" + styles.toString() + "</style>"
                + "</head><body>" + CookieTest.MENU 
                + "<div class='title'>"
                + "Session Management with Cookies:</div>");
        writer.print("<div>");

        // cookies will be null if there's no cookie
        if (cookies == null) {
            writer.print("No cookie in this HTTP response.");
        } else {
            writer.println("<br/>Cookies in this HTTP response:");
            for (Cookie cookie : cookies) {
                writer.println("<br/>" + cookie.getName() + ":"
                        + cookie.getValue());
            }
        }
        writer.print("</div>");
        writer.print("</body></html>");
    }
}