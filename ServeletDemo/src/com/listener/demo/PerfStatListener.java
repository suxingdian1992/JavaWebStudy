package com.listener.demo;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author suxin
 * 2017��12��7��
 * prjName:ServeletDemo pakName:com.listener.demo
 * TODO
 * ����ÿ��servletcontext�Ӵ��������ٵ�ʱ��
 * 2017��12��7��
 */
@WebListener
public class PerfStatListener implements ServletRequestListener {
    
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        //��servlet��ʼ���е���һ��nanotime��¼��ʼʱ�̲����������start������
        servletRequest.setAttribute("start", System.nanoTime());
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletRequestListener#requestDestroyed(javax.servlet.ServletRequestEvent)
     */
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        //ȡ�ô�����ʱ���
        Long start = (Long) servletRequest.getAttribute("start");
        //����servlet���ٵ�ʱ���¼ʱ�䣬����ʱ���͵õ�httpsession
        Long end = System.nanoTime();
        HttpServletRequest httpServletRequest = 
                (HttpServletRequest) servletRequest;
        String uri = httpServletRequest.getRequestURI();
        System.out.println("time taken to execute " + uri +
                ":"  + ((end - start) / 1000) + "microseconds");
    }
}