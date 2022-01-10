package com.makkras.shop.controller.command.impl;

import com.google.gson.Gson;
import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.validator.impl.CustomOrderDataValidator;
import com.makkras.shop.entity.CompleteOrder;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.OrderService;
import com.makkras.shop.util.locale.LocalizedTextExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class FindOrderCommand implements CustomCommand {
    private static final String EMPTY_COMPLETION_STATUS = "-";
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Gson gson = new Gson();
        String rawLogin = request.getParameter(Literal.LOGIN_NAME);
        String rawStartDate = request.getParameter(Literal.ORDER_START_DATE);
        String rawEndDate = request.getParameter(Literal.ORDER_END_DATE);
        String rawCompletionStatus = request.getParameter(Literal.ORDER_COMPLETION_STATUS);
        String currentLocale = request.getSession().getAttribute(Literal.LOCALE_NAME).toString();
        LocalizedTextExtractor localizedTextExtractor = LocalizedTextExtractor.getInstance();
        if(CustomOrderDataValidator.getInstance().validateOrderSearchData(rawLogin,rawStartDate,rawEndDate)){
            String login = rawLogin;
            LocalDate startDate = LocalDate.of(0,1,1);
            if(!rawStartDate.isBlank()) {
                startDate = LocalDate.parse(rawStartDate);
            }
            LocalDate endDate = LocalDate.of(9999,12,31);
            if(!rawEndDate.isBlank()) {
                endDate = LocalDate.parse(rawEndDate);
            }
            Boolean completionStatus = true;
            if(rawCompletionStatus.equals(Literal.IN_PROGRESS_ORDER_STATUS)) {
                completionStatus = false;
            } else if(rawCompletionStatus.equals(EMPTY_COMPLETION_STATUS)) {
                completionStatus = null;
            }
            OrderService service = OrderService.getInstance();
            try {
                List<CompleteOrder> orders  = service.findOrdersFromDbByParams(login,startDate,endDate,completionStatus);

                String ordersInGson = gson.toJson(orders);
                request.setAttribute(Literal.ORDERS_FOR_ADMIN,ordersInGson);
                page = PagePath.RAW_MAIN_ADMIN_ORDERS_VIEW_PAGE;
            } catch (ServiceException e) {
                logger.error(e.getMessage());
            }
        } else {
            request.setAttribute(Literal.AUTHORIZATION_ERROR_MESSAGE,
                    localizedTextExtractor.getText(currentLocale,"INVALID_FORM_DATA_ERROR"));
            page = PagePath.AUTHORIZATION_PAGE;
        }
        return page;
    }
}
