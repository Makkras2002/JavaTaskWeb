package com.makkras.shop.controller;

import com.makkras.shop.controller.command.CustomCommand;
import com.makkras.shop.controller.command.impl.*;

public enum CommandType {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT{
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    CONFIRM_REGISTRATION {
        {
            this.command = new ConfRegCommand();
        }
    },
    EMPTY {
        {
            this.command = new EmptyCommand();
        }
    },
    PREPARE_MAIN_CLIENT_PAGE {
        {
            this.command = new FillMainClientMenuCommand();
        }
    },
    CHANGE_LOCALE {
        {
            this.command = new ChangeLocaleCommand();
        }
    },
    CHANGE_LOGIN {
        {
            this.command = new ChangeLoginCommand();
        }
    },
    CHANGE_PASSWORD {
        {
            this.command = new ChangePasswordCommand();
        }
    },
    SORT_PRODUCTS_BY_NAME {
        {
            this.command = new SortProductsByNameCommand();
        }
    },
    SORT_PRODUCTS_BY_CATEGORY {
        {
            this.command = new SortProductsByCategoryCommand();
        }
    },
    SORT_PRODUCTS_BY_PRICE {
        {
            this.command = new SortProductsByPriceCommand();
        }
    },
    FIND_PRODUCT {
        {
            this.command = new FindProductCommand();
        }
    },
    ADD_PRODUCT_TO_BUCKET {
        {
            this.command = new AddProductToOrderCommand();
        }
    },
    OPEN_USER_BASKET {
        {
            this.command = new OpenUserOrderCommand();
        }
    },
    OPEN_ORDER_PAGE_WRAPPER {
        {
            this.command = new OpenOrderPageWrapperCommand();
        }
    },
    REMOVE_PRODUCT_FROM_ORDER {
        {
            this.command = new RemoveProductFromOrderCommand();
        }
    },
    MAKE_ORDER {
        {
            this.command = new MakeOrderCommand();
        }
    },
    PREPARE_MAIN_ADMIN_PAGE {
        {
            this.command = new FillMainAdminMenuCommand();
        }
    },
    PREPARE_PRODUCT_ADDING_PAGE {
        {
            this.command = new FillProductAddingMenuCommand();
        }
    },
    ADD_PRODUCT_TO_CATALOG {
        {
            this.command = new AddProductToCatalogCommand();
        }
    };
    CustomCommand command;
    public CustomCommand getCurrentCommand(){
        return command;
    }
    public static CustomCommand defineCommand(String requestCommand){
        CustomCommand command;
        CommandType currentEnum;
        try {
            currentEnum = CommandType.valueOf(requestCommand.toUpperCase());
        } catch (IllegalArgumentException e){
            currentEnum = CommandType.EMPTY;
        }
        command =currentEnum.getCurrentCommand();
        return command;
    }
}
