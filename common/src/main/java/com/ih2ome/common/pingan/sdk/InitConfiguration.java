package com.ih2ome.common.pingan.sdk;

import com.ih2ome.common.utils.properties.PropertiesConfigUtil;
import com.pabank.sdk.PABankSDK;
import org.springframework.stereotype.Component;

/**
 * @author Sky
 * create 2018/06/19
 * email sky.li@ixiaoshuidi.com
 **/
@Component
public class InitConfiguration {

    private static final String cerName = "open.cer";

    private static final String pfxName = "test.pfx";

    private static final String protocolName = "protocol.xml";

    //初始化配置(sdk)
    public static void init(String cerName, String pfxName, String protocolName) {
//        System.out.println(File.separator);
//        硬盘地址
        String dirPath = InitConfiguration.class.getClassLoader().getResource("sdkConfig").getPath();
        //配置文件地址
        String configProperties = InitConfiguration.class.getClassLoader().getResource("sdkConfig/config.properties").getPath();
        //初始化sdk配置文件
        PropertiesConfigUtil.initProperties(configProperties);
//        设置公钥的绝对路径
        Object public_key_path = PropertiesConfigUtil.getKeyValue("PUBLIC_KEY_PATH");
        if (cerName.equals(public_key_path)) {
            PropertiesConfigUtil.updateProperties("PUBLIC_KEY_PATH", dirPath + "/" + public_key_path);
        }
        //设置协议的绝对路径
        Object protocol_path = PropertiesConfigUtil.getKeyValue("PROTOCOL_PATH");
        if (protocolName.equals(protocol_path)) {
            PropertiesConfigUtil.updateProperties("PROTOCOL_PATH", dirPath + "/" + protocol_path);
        }
        //设置正数的绝对路径
        String keypath = PropertiesConfigUtil.getKeyValue("KEYPATH");
        if (pfxName.equals(keypath)) {
            PropertiesConfigUtil.updateProperties("KEYPATH", dirPath + "/" + keypath);
        }
        System.out.println(PropertiesConfigUtil.getKeyValue("PUBLIC_KEY_PATH"));
        System.out.println(PropertiesConfigUtil.getKeyValue("PROTOCOL_PATH"));
        System.out.println(PropertiesConfigUtil.getKeyValue("KEYPATH"));
        //初始化配置
        PABankSDK.init(configProperties);
    }
}
