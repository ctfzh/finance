package com.ih2ome.common.utils.pingan;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.PageVO.PinganWxSignBuildVO;
import com.ih2ome.common.PageVO.PinganWxSignVerifyVO;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;

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

    public static Boolean vertifySign(JSONObject respObject) {
        String respSign = respObject.get("sign").toString();
        respObject.remove("sign");
        try {
            //按照A-Z排序
            String sort = sort(respObject);
            System.out.println(sort);
            //sha1加密
            String sha1Hex = Sha1Util.SHA1(sort);
            //md5加密
            String sign = MD5Util.MD5Encode(sha1Hex).toLowerCase();
            return sign.equals(respSign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //按照A-Z排序
    public static String sort(Map paramMap) throws Exception {
        String sort = "";
        TLinxMapUtil signMap = new TLinxMapUtil();
        if (paramMap != null) {
            String key;
            for (Iterator it = paramMap.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                String value = ((paramMap.get(key) != null) && (!("".equals(paramMap.get(key).toString())))) ? paramMap.get(key).toString() : "";
                signMap.put(key, value);
            }
            signMap.sort();
            for (Iterator it = signMap.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                sort = sort + key + "=" + signMap.get(key).toString() + "&";
            }
            if ((sort != null) && (!("".equals(sort)))) {
                sort = sort.substring(0, sort.length() - 1);
            }
        }
        return sort;
    }


}
