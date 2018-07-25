package com.ih2ome.common.utils.pingan;


import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;

import java.security.MessageDigest;

import org.slf4j.LoggerFactory;

/**
 * @author Sky
 * create 2018/07/24
 * email sky.li@ixiaoshuidi.com
 * <p>
 * Sha1加密
 **/
public class Sha1Util {
    private static final Logger logger = LoggerFactory.getLogger(Sha1Util.class);

    public static String encrypt(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            logger.error("sha1 加密异常");
            return null;
        }
    }


    public static void main(String[] args) {
        String origin = "data=DATA&open_id=txafCXQt058248b3230c9081ff90ce80&open_key=aG0ck19g2HdthGRdSCfmiloOoGXoOzWZ&timestamp=1234567890";
        String sha1 = DigestUtils.sha1Hex(origin);
        System.out.println(sha1);
        String sha2 = Sha1Util.encrypt(origin);
        System.out.println(sha2);
        String md5 = DigestUtils.md5Hex(sha2);
        System.out.println(md5);
    }
}
