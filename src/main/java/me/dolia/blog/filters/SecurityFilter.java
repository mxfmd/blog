package me.dolia.blog.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {
    public void destroy() {
        //NOP
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        //NOP
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        if (((HttpServletRequest) req).getSession().getAttribute("username") == null) {
            String redirectURL = ((HttpServletResponse) resp).encodeRedirectURL("/login?from=" + ((HttpServletRequest) req).getRequestURI());
            ((HttpServletResponse) resp).sendRedirect(redirectURL);
        } else {
            chain.doFilter(req, resp);
        }
    }


}
