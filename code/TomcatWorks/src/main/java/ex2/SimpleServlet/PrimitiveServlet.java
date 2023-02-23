package ex2.SimpleServlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author hjz
 */
public class PrimitiveServlet implements Servlet {
    //init，service 和 destroy 是 servlet 的生命周期方法

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }

    /**
     * service 方法从ServletResponse 对象获得 java.io.PrintWriter 实例，
     * 并发送字符串到浏览器去。
     * @param request 包括客户端的 HTTP 请求信息
     * @param response 封装 servlet 的响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter out = response.getWriter();
        out.println("Hello. Roses are red.");
        out.print("Violets are blue.");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }


}
