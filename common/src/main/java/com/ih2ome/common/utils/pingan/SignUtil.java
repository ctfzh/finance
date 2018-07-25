package com.ih2ome.common.utils.pingan;

import com.ih2ome.common.PageVO.PinganWxSignBuildVO;
import com.ih2ome.common.PageVO.PinganWxSignVerifyVO;
import org.apache.commons.codec.digest.DigestUtils;

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
        String sha1Hex = DigestUtils.sha1Hex(origin.toString());
        //md5加密
        String sign = DigestUtils.md5Hex(sha1Hex);
        return sign;
    }


    public static Boolean vertifySign(String origin, PinganWxSignVerifyVO target) {
        //sha1加密
        String sha1Hex = DigestUtils.sha1Hex(target.toString());
        //md5加密
        String sign = DigestUtils.md5Hex(sha1Hex);
        return origin.equals(sign);
    }


}
