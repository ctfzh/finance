package com.ih2ome.common.utils.pingan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Sky
 * create 2018/07/27
 * email sky.li@ixiaoshuidi.com
 * 流水号生成工具类
 **/
public class SerialNumUtil {
    public static final String NUMBERCHAR = "0123456789";


    /**
     * 生成流水号：yyyyMMdd+8位随机数字字符串
     *
     * @return
     */
    public static String generateSerial() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        return date + generateNumber(8);
    }


    /**
     * 返回一个定长的随机数字字符串
     *
     * @param length
     * @return
     */
    public static String generateNumber(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
        }
        return sb.toString();
    }

}
