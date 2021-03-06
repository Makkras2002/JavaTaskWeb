package com.makkras.shop.controller.listener;

import com.makkras.shop.pool.CustomConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        CustomConnectionPool.getInstance().destroyPool();
    }
}
