package com.ih2ome.server.config;

import com.ih2ome.server.pingan.sdk.InitConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Sky
 * create 2018/07/19
 * email sky.li@ixiaoshuidi.com
 **/
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Value("${pinganSDKPrefix}")
    private String pinganSDKPrefix;

    @Override
    public void run(String... args) throws Exception {
        //SDK的初始化操作
        System.out.println("SDK的初始化操作");
        InitConfiguration.init(pinganSDKPrefix);
    }
}
