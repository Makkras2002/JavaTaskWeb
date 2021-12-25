package com.makkras.shop.controller.filter;

import com.makkras.shop.controller.util.Literal;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter(urlPatterns = { "/pages/*" },
        initParams = { @WebInitParam(name = "INDEX_PATH", value = Literal.MAIN_CLIENT_PAGE)})
public class PageDirectAccessRedirectFilter implements Filter {
    private String indexPath;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter("INDEX_PATH");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURL = String.valueOf(request.getRequestURL());
        if(requestURL.contains(Literal.AUTHORIZATION_PAGE) || requestURL.contains(Literal.REGISTRATION_PAGE)){
            filterChain.doFilter(request,response);
        } else {
            response.sendRedirect(request.getContextPath() + indexPath);
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {
    }
}
