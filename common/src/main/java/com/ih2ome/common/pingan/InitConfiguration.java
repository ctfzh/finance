package com.ih2ome.common.pingan;

import com.pabank.sdk.PABankSDK;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

/**
 * @author Sky
 * create 2018/06/19
 * email sky.li@ixiaoshuidi.com
 **/
public class InitConfiguration {

    //初始化配置
    public static void init() {
        String dirPath = InitConfiguration.class.getClassLoader().getResource("config").getPath();
        System.out.println(dirPath);
    }
}
