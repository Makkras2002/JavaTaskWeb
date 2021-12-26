package com.makkras.shop.controller.filter;

import com.makkras.shop.controller.CommandType;
import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.entity.UserRole;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = { "/controller" })
public class RoleAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String command = request.getParameter(Literal.COMMAND);
        RequestDispatcher requestDispatcher = null;
        String page = null;
        String currentLocale = String.valueOf(session.getAttribute(Literal.LOCALE_NAME));
        LocalizedTextExtractor localizedTextExtractor  = LocalizedTextExtractor.getInstance();
        if(session.getAttribute(Literal.ROLE) == null){
            if(command.equals(CommandType.REGISTER.toString().toLowerCase()) ||
                    command.equals(CommandType.LOGIN.toString().toLowerCase()) ||
                    command.equals(CommandType.PREPARE_MAIN_CLIENT_PAGE.toString().toLowerCase()) ||
                    command.equals(CommandType.CHANGE_LOCALE.toString().toLowerCase()) ||
                    command.equals(CommandType.SORT_PRODUCTS_BY_NAME.toString().toLowerCase()) ||
                    command.equals(CommandType.SORT_PRODUCTS_BY_CATEGORY.toString().toLowerCase()) ||
                    command.equals(CommandType.SORT_PRODUCTS_BY_PRICE.toString().toLowerCase()) ||
                    command.equals(CommandType.FIND_PRODUCT.toString().toLowerCase())){
                filterChain.doFilter(request,response);
            } else {
                page = Literal.AUTHORIZATION_PAGE;
                requestDispatcher = request.getServletContext().getRequestDispatcher(page);
                request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                        localizedTextExtractor.getText(currentLocale,"EXPIRED_SESSION_ERROR"));
                requestDispatcher.forward(request,response);
            }
        } else {
            if(String.valueOf(session.getAttribute(Literal.ROLE)).equals(UserRole.CLIENT.toString())){
                if(2 == 1){
                    page = Literal.AUTHORIZATION_PAGE;
                    requestDispatcher = request.getServletContext().getRequestDispatcher(page);
                    request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                            localizedTextExtractor.getText(currentLocale,"WRONG_ROLE_ACCESS_DENIAL"));
                    requestDispatcher.forward(request,response);
                } else {
                    filterChain.doFilter(request,response);
                }
            } else {
                filterChain.doFilter(request,response);
            }
        }
    }
    @Override
    public void destroy() {

    }
}
