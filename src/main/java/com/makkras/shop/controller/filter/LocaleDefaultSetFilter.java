package com.makkras.shop.controller.filter;


import com.makkras.shop.controller.Literal;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class LocaleDefaultSetFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        if(request.getSession().getAttribute(Literal.LOCALE_NAME) == null){
            request.getSession().setAttribute(Literal.LOCALE_NAME, Literal.DEFAULT_LOCALE);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
