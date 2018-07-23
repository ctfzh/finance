package com.ih2ome.server.pingan.sdk;

import com.ih2ome.common.utils.properties.PropertiesConfigUtil;
import com.pabank.sdk.PABankSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author Sky
 * create 2018/06/19
 * email sky.li@ixiaoshuidi.com
 **/
public class InitConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitConfiguration.class);

    //初始化配置(sdk)
    public static void init(String prefix) {
        String configProperties = InitConfiguration.class.getClassLoader().getResource(prefix + "_sdkConfig/config.properties").getPath();
        PropertiesConfigUtil.initProperties(configProperties);
        LOGGER.info("PUBLIC_KEY_PATH：{}", PropertiesConfigUtil.getKeyValue("PUBLIC_KEY_PATH"));
        LOGGER.info("PROTOCOL_PATH：{}", PropertiesConfigUtil.getKeyValue("PROTOCOL_PATH"));
        LOGGER.info("KEYPATH：{}", PropertiesConfigUtil.getKeyValue("KEYPATH"));
        //初始化配置（当文件目录都正确）
        PABankSDK.init(configProperties);
        LOGGER.info("-----------------平安SDK初始化成功----------------");
        LOGGER.info("-----------------验证开发者----------------");
//        Map<String, Object> returnApprMap = PABankSDK.getInstance().approveDev();
//        Object appAccessToken = returnApprMap.get("appAccessToken");
//        System.out.println(appAccessToken);
    }
}
