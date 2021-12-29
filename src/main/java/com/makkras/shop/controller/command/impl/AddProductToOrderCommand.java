package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.util.Literal;
import com.makkras.shop.controller.util.PagePath;
import com.makkras.shop.entity.ComponentOrder;
import com.makkras.shop.entity.Product;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class AddProductToOrderCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        ProductService productService = ProductService.getInstance();
        Long productId = Long.parseLong(request.getParameter(Literal.PRODUCT_ID));
        Long productAmount = Long.parseLong(request.getParameter(Literal.PRODUCT_AMOUNT));
        try {
            page = PagePath.MAIN_CLIENT_PAGE;
            Optional<Product> foundProductInDbById = productService.findProductById(productId);
            if(foundProductInDbById.isPresent()){
                List<ComponentOrder> ordersList = (List<ComponentOrder>) request.getSession().getAttribute(Literal.ORDER);
                ComponentOrder newComponentToAddInOrder = new ComponentOrder(foundProductInDbById.get(), productAmount,
                        foundProductInDbById.get().getProductPrice().multiply(BigDecimal.valueOf(productAmount)));
                ordersList.add(newComponentToAddInOrder);
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}
