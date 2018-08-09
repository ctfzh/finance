package com.ih2ome.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.PageVO.*;
import com.ih2ome.common.utils.BeanMapUtil;
import com.ih2ome.common.utils.pingan.AESUtil;
import com.ih2ome.common.utils.pingan.HttpUtil;
import com.ih2ome.common.utils.pingan.HttpsUtil;
import com.ih2ome.common.utils.pingan.SignUtil;
import com.ih2ome.service.PinganConfigService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

/**
 * @author Sky
 * create 2018/07/27
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class PinganConfigServiceImpl implements PinganConfigService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PinganPayServiceImpl.class);

    //测试环境地址：https://mixpayuat4.orangebank.com.cn/mct1/
    //生产环境地址: https://api.orangebank.com.cn/mct1/
    @Value("${pingan.wxPay.baseUrl}")
    private String baseUrl;
    //商户门店的唯一标识
    @Value("${pingan.wxPay.open_id}")
    private String open_id;
    @Value("${pingan.wxPay.open_key}")
    private String open_key;

    /**
     * 商户支付配置 ---添加
     *
     * @param pinganWxConfigAddReqVO
     * @return
     */
    @Override
    public PinganWxConfigAddResVO addConfig(PinganWxConfigAddReqVO pinganWxConfigAddReqVO) {
        //请求对象PinganWxRequestVO生成。
        PinganWxRequestVO pinganWxRequestVO = getPinganWxRequestVO(pinganWxConfigAddReqVO);
        String url = baseUrl + "mpconfig/add";
        TreeMap<String, String> treeMap = BeanMapUtil.objectToMap(pinganWxRequestVO);
        String resultJson = handlePost(url, treeMap);
        PinganWxSignVerifyVO pinganWxSignVerifyVO = JSONObject.parseObject(resultJson, PinganWxSignVerifyVO.class);
        pinganWxSignVerifyVO.setOpen_key(open_key);
        //请求成功
        if (pinganWxSignVerifyVO.getErrcode().equals("0")) {
            //data数据不为空(需要验证签名)
            if (StringUtils.isNotEmpty(pinganWxSignVerifyVO.getData())) {
                //校验签名。
                Boolean flag = SignUtil.vertifySign(pinganWxSignVerifyVO);
                if (flag) {
                    //验证签名成功,拿到返回的响应data数据
                    String decryptData = AESUtil.decrypt(pinganWxSignVerifyVO.getData(), open_key);
                    PinganWxConfigAddResVO pinganWxConfigAddResVO = JSONObject.parseObject(decryptData, PinganWxConfigAddResVO.class);
                    return pinganWxConfigAddResVO;
                } else {
                    LOGGER.error("签名校验失败,签名信息:{}", pinganWxSignVerifyVO.getSign());
                }
            } else {
                LOGGER.info("无data数据,无需验签!");
            }
        } else {
            LOGGER.error("请求失败,失败原因:{}", pinganWxSignVerifyVO.toString());
        }
        return null;
    }

    @Override
    public void getConfig(PinganWxConfigAddReqVO pinganWxConfigAddReqVO) {
        //请求对象PinganWxRequestVO生成。
        PinganWxRequestVO pinganWxRequestVO = getPinganWxRequestVO(pinganWxConfigAddReqVO);
        String url = baseUrl + "mpconfig/query";
        TreeMap<String, String> treeMap = BeanMapUtil.objectToMap(pinganWxRequestVO);
        String resultJson = handlePost(url, treeMap);
        PinganWxSignVerifyVO pinganWxSignVerifyVO = JSONObject.parseObject(resultJson, PinganWxSignVerifyVO.class);
        pinganWxSignVerifyVO.setOpen_key(open_key);
        //请求成功
        if (pinganWxSignVerifyVO.getErrcode().equals("0")) {
            //data数据不为空(需要验证签名)
            if (StringUtils.isNotEmpty(pinganWxSignVerifyVO.getData())) {
                //校验签名。
                Boolean flag = SignUtil.vertifySign(pinganWxSignVerifyVO);
                if (flag) {
                    //验证签名成功,拿到返回的响应data数据
                    String decryptData = AESUtil.decrypt(pinganWxSignVerifyVO.getData(), open_key);
                    System.out.println(decryptData);
                } else {
                    LOGGER.error("签名校验失败,签名信息:{}", pinganWxSignVerifyVO.getSign());
                }
            } else {
                LOGGER.info("无data数据,无需验签!");
            }
        } else {
            LOGGER.error("请求失败,失败原因:{}", pinganWxSignVerifyVO.toString());
        }
    }


    //请求数据处理(data数据AES加密,签名生成。)
    private PinganWxRequestVO getPinganWxRequestVO(Object object) {
        //对pinganWxPayListVO进行AES加密
        String data = AESUtil.encrypt(JSONObject.toJSONString(object), open_key);
        //获取当前系统时间
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        //生成签名(签名组装)
        PinganWxSignBuildVO pinganWxSignBuildVO = new PinganWxSignBuildVO();
        pinganWxSignBuildVO.setTimestamp(timestamp);
        pinganWxSignBuildVO.setOpen_id(open_id);
        pinganWxSignBuildVO.setOpen_key(open_key);
        pinganWxSignBuildVO.setData(data);
        String sign = SignUtil.generateSign(pinganWxSignBuildVO);
        //平安请求对象
        PinganWxRequestVO pinganWxRequestVO = new PinganWxRequestVO();
        BeanUtils.copyProperties(pinganWxSignBuildVO, pinganWxRequestVO);
        pinganWxRequestVO.setSign(sign);
        return pinganWxRequestVO;
    }

    /**
     * http&https---post请求
     *
     * @param postmap
     * @return 响应字符串
     */
    private String handlePost(String url, TreeMap<String, String> postmap) {
        System.out.println("url----:" + url);
        if (url.contains("https")) {
            return HttpsUtil.httpMethodPost(url, postmap, "UTF-8");
        } else {
            return HttpUtil.httpMethodPost(url, postmap, "UTF-8");
        }
    }

}
