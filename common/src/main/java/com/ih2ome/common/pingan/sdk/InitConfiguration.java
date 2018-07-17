package com.ih2ome.common.pingan.sdk;

import com.ih2ome.common.utils.properties.PropertiesConfigUtil;
import com.pabank.sdk.PABankSDK;
import jdk.internal.util.xml.impl.Input;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Sky
 * create 2018/06/19
 * email sky.li@ixiaoshuidi.com
 **/
@Component
public class InitConfiguration {

    //初始化配置(sdk)
    public static void init(String cerName, String pfxName, String protocolName) {

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
        System.out.println(PropertiesConfigUtil.getKeyValue("PUBLIC_KEY_PATH"));
        System.out.println(PropertiesConfigUtil.getKeyValue("PROTOCOL_PATH"));
        System.out.println(PropertiesConfigUtil.getKeyValue("KEYPATH"));
        //初始化配置（当文件目录都正确）
        PABankSDK.init(configProperties);
    }
}
