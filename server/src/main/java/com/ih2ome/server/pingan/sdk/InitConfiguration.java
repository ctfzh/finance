package com.ih2ome.server.pingan.sdk;

import com.ih2ome.common.utils.properties.PropertiesConfigUtil;
import com.pabank.sdk.PABankSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;

/**
 * @author Sky
 * create 2018/06/19
 * email sky.li@ixiaoshuidi.com
 **/
public class InitConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitConfiguration.class);

    //初始化配置(sdk)
    public static void init(String prefix) {
        LOGGER.info("==============================初始化操作=======================");
        String configProperties = InitConfiguration.class.getClassLoader().getResource(prefix + "_sdkConfig/config.properties").getPath();
        /*
//        硬盘地址
        String dirPath = InitConfiguration.class.getClassLoader().getResource("sdkConfig").getPath();
        //配置文件地址
        String configProperties = InitConfiguration.class.getClassLoader().getResource("sdkConfig/config.properties").getPath();
        InputStream configInputStream = InitConfiguration.class.getResourceAsStream("sdkConfig/config.properties");
        //初始化sdk配置文件
        PropertiesConfigUtil.initProperties(configProperties);
//        设置公钥的绝对路径（公钥）
        String public_key_path = PropertiesConfigUtil.getKeyValue("PUBLIC_KEY_PATH");
        if (!public_key_path.startsWith(dirPath)) {
            PropertiesConfigUtil.updateProperties("PUBLIC_KEY_PATH", dirPath + "/" + cerName);
        }
        //设置协议的绝对路径
        String protocol_path = PropertiesConfigUtil.getKeyValue("PROTOCOL_PATH");
        if (!protocol_path.startsWith(dirPath)) {
            PropertiesConfigUtil.updateProperties("PROTOCOL_PATH", dirPath + "/" + protocolName);
        }
        //设置证书的绝对路径（私钥）
        String keypath = PropertiesConfigUtil.getKeyValue("KEYPATH");
        if (!keypath.startsWith(dirPath)) {
            PropertiesConfigUtil.updateProperties("KEYPATH", dirPath + "/" + pfxName);
        }
        */
        PropertiesConfigUtil.initProperties(configProperties);
        LOGGER.info("-----------------PUBLIC_KEY_PATH：{}----------------", PropertiesConfigUtil.getKeyValue("PUBLIC_KEY_PATH"));
        LOGGER.info("-----------------PROTOCOL_PATH：{}----------------", PropertiesConfigUtil.getKeyValue("PROTOCOL_PATH"));
        LOGGER.info("-----------------KEYPATH：{}----------------", PropertiesConfigUtil.getKeyValue("KEYPATH"));
        //初始化配置（当文件目录都正确）
        PABankSDK.init(configProperties);
        LOGGER.info("-----------------平安SDK初始化成功----------------");
    }
}
