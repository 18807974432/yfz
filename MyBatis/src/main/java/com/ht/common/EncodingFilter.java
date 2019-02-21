package com.ht.common;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    String encoding;
    @Override
    public void destroy() {

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding=filterConfig.getInitParameter("encoding");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if(encoding!=null && !encoding.equals("")){
            servletRequest.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
