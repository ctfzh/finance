package com.ih2ome.server.config;

import com.ih2ome.server.pingan.sdk.InitConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Sky
 * create 2018/07/17
 * email sky.li@ixiaoshuidi.com
 **/
//@Component
//@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Value("${pinganSDKPrefix}")
    private String pinganSDKPrefix;

    @Override

    public void contextInitialized(ServletContextEvent sce) {
        //SDK的初始化操作
        System.out.println("SDK的初始化操作");
        InitConfiguration.init(pinganSDKPrefix);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
