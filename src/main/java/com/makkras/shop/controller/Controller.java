package com.makkras.shop.controller;

import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;


@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }
    private void processRequest(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String page;
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        if(req.getSession().getAttribute(Literal.LOCALE_NAME) == null){
            req.getSession().setAttribute(Literal.LOCALE_NAME, Literal.DEFAULT_LOCALE);
        }
        String currentLocale = req.getSession().getAttribute(Literal.LOCALE_NAME).toString();
        CustomCommand command = CommandType.defineCommand(req.getParameter(Literal.COMMAND));
        page = command.execute(req);
        if(page!= null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req,resp);
        } else {
            page = PagePath.AUTHORIZATION_PAGE;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            req.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                    localizedTextExtractor.getText(currentLocale,"UNEXPECTED_ERROR"));
            dispatcher.forward(req,resp);
        }
    }
}
