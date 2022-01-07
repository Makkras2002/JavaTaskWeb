package com.makkras.shop.listener;

import com.makkras.shop.pool.CustomConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        CustomConnectionPool.getInstance().destroyPool();
    }
}
