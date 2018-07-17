package com.ih2ome.server.config;

import com.ih2ome.common.pingan.sdk.InitConfiguration;
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
@Component
//@WebListener
public class MyServletContextListener implements ServletContextListener {

    //.cer开放平台证书(公钥)
    @Value("${pinganSDK.cerName}")
    private String cerName;

    //.pfx橙E网证书
    @Value("${pinganSDK.pfxName}")
    private String pfxName;

    //协议文件(名称)
    @Value("${pinganSDK.protocalName}")
    private String protocolName;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //SDK的初始化操作
        System.out.println("SDK的初始化操作");
        InitConfiguration.init(cerName, pfxName, protocolName);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
