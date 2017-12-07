package com.listener.demo;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author suxin
 * 2017年12月7日
 * prjName:ServeletDemo pakName:com.listener.demo
 * TODO
 * 计算每个servletcontext从创建到销毁的时间
 * 2017年12月7日
 */
@WebListener
public class PerfStatListener implements ServletRequestListener {
    
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        //在servlet初始化中调用一次nanotime记录开始时刻并放在请求的start属性中
        servletRequest.setAttribute("start", System.nanoTime());
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletRequestListener#requestDestroyed(javax.servlet.ServletRequestEvent)
     */
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        //取得创建的时间点
        Long start = (Long) servletRequest.getAttribute("start");
        //再在servlet销毁的时候记录时间，两个时间差就得到httpsession
        Long end = System.nanoTime();
        HttpServletRequest httpServletRequest = 
                (HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();
        System.out.println("time taken to execute " + uri +
                ":"  + ((end - start) / 1000) + "microseconds");
    }
}