package com.makkras.shop.controller.command.impl;

import com.makkras.shop.controller.Literal;
import com.makkras.shop.controller.PagePath;
import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.exception.ServiceException;
import com.makkras.shop.service.impl.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ShowProductsSellingStatisticsCommand implements CustomCommand {
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        ProductService productService = ProductService.getInstance();
        try {
            Map<String,Integer> productsSellingStatistics = productService.findAllProductsSellingStatistics();
            request.setAttribute(Literal.PRODUCTS_SELLING_STATISTICS,productsSellingStatistics);
            page = PagePath.PRODUCT_SELLING_STATISTICS_PAGE;
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return page;
    }
}
