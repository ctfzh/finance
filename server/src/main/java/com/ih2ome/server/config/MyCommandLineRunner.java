package com.ih2ome.server.config;

import com.ih2ome.common.pingan.sdk.InitConfiguration;
import com.ih2ome.common.utils.properties.PropertiesConfigUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Sky
 * create 2018/06/19
 * email sky.li@ixiaoshuidi.com
 **/
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    //.cer开放平台证书(公钥)
    @Value("${pinganSDK.cerName}")
    private String cerName;

    //.pfx橙E网证书
    @Value("${pinganSDK.pfxName}")
    private String pfxName;

    //协议文件(名称)
    @Value("${pinganSDK.protocalName}")
    private String protocolName;

    @Value("${pinganSDK.dirPath}")
    private String dirPath;

    @Override
    public void run(String... var1) throws Exception {
        //修改配置文件内容
        PropertiesConfigUtil.initProperties(dirPath);
        //设置公钥
        PropertiesConfigUtil.updateProperties("PUBLIC_KEY_PATH", cerName);
        //设置私钥
        PropertiesConfigUtil.updateProperties("KEYPATH", pfxName);
        //设置协议
        PropertiesConfigUtil.updateProperties("PROTOCOL_PATH", protocolName);
        //SDK的初始化操作
        InitConfiguration.init(cerName, pfxName, protocolName);
    }
}
