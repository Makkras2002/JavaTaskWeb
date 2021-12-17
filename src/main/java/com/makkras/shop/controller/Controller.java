package com.makkras.shop.controller;

import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.util.PathManager;
import com.makkras.shop.exception.ServiceException;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }
    private void processRequest(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String page;
        CustomCommand command = CommandType.defineCommand(req.getParameter(Literal.COMMAND));
        page = command.execute(req);
        if(page!= null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req,resp);
        } else {
            try {
                page = PathManager.getInstance().getProperty("path.page.auth");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                req.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,"Unexpected error!!!");
                dispatcher.forward(req,resp);
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
