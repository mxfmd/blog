package me.dolia.blog.filters;

import javax.servlet.*;
import java.io.IOException;

public class SetCharacterEncodingFilter implements Filter {

    private String encoding;

    public void destroy() {
        //NOP
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(encoding);

        // Set the default response content type and encoding
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        this.encoding = config.getInitParameter("requestEncoding");
        if (null == this.encoding) {
            this.encoding = "UTF-8";
        }
    }

}
