package com.ih2ome.common.utils.properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * @author Sky
 * create 2018/06/21
 * email sky.li@ixiaoshuidi.com
 **/
public class PropertiesConfigUtil {
    private static SafeProperties props = new SafeProperties();
    private static String profilePath;
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesConfigUtil.class);

    /**
     * 初始化配置文件
     *
     * @param filePath
     */
    public static void initProperties(String filePath) {
        try {
            profilePath = filePath;
            InputStream inputStream = new FileInputStream(filePath);
            props.load(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("初始化配置文件错误,配置文件没有找到,错误信息:{}", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("初始化配置文件错误,IO流异常,错误信息:{}", e.getMessage());
        }
    }

    /**
     * 读取属性文件中相应键的值
     *
     * @param key 主键
     * @return String
     */
    public static String getKeyValue(String key) {
        return props.getProperty(key);
    }

    /**
     * 根据主键key读取主键的值value
     *
     * @param filePath 属性文件路径
     * @param key      键名
     */
    public static String readValue(String filePath, String key) {
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(
                    filePath));
            props.load(in);
            String value = props.getProperty(key);
            System.out.println(key + "键的值是：" + value);
            in.close();
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 更新properties文件的键值对
     * 如果该主键已经存在，更新该主键的值；
     * 如果该主键不存在，则插件一对键值。
     *
     * @param keyname  键名
     * @param keyvalue 键值
     */
    public static void updateProperties(String keyname, String keyvalue) {
        try {
            OutputStream fos = new FileOutputStream(profilePath);
            props.put(keyname, keyvalue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, null);
            fos.close();
        } catch (IOException e) {
            LOGGER.error("config.properties属性文件更新错误");
        }
    }

}
