package com.ih2ome.common.utils.pingan;

import com.ih2ome.common.PageVO.PinganWxSignBuildVO;
import com.ih2ome.common.PageVO.PinganWxSignVerifyVO;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

/**
 * @author Sky
 * create 2018/07/24
 * email sky.li@ixiaoshuidi.com
 **/
public class SignUtil {

    /**
     * 生成平安的sign。
     *
     * @param origin
     * @return
     */
    public static String generateSign(PinganWxSignBuildVO origin) {
        //sha1加密
        String sha1Hex = Sha1Util.SHA1(origin.buildString());
        System.out.println("sha1加密后======>:" + sha1Hex);
        //md5加密
        String sign = MD5Util.MD5Encode(sha1Hex).toLowerCase();
        System.out.println("md5加密后======>:" + sign);
        return sign;
    }


    public static Boolean vertifySign(PinganWxSignVerifyVO target) {
        String origin = target.getSign();
        System.out.println("验证签名数据======>:" + target.buildString());
        System.out.println("签名原始数据======>:" + target.getSign());
        //sha1加密
        String sha1Hex = Sha1Util.SHA1(target.buildString());
        System.out.println("sha1加密后======>：" + sha1Hex);
        //md5加密
        String sign = MD5Util.MD5Encode(sha1Hex).toLowerCase();
        System.out.println("md5加密后======>：" + sign);
        return origin.equals(sign);
    }


}
