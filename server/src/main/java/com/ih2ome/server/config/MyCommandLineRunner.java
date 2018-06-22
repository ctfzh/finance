package com.ih2ome.server.config;

import com.ih2ome.common.pingan.sdk.InitConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Sky
 * create 2018/06/19
 * email sky.li@ixiaoshuidi.com
 **/
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... var1) throws Exception {
        //SDK的初始化操作
        InitConfiguration.init();
    }
}
