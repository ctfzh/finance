package com.ih2ome.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.Exception.PinganApiException;
import com.ih2ome.common.PageVO.*;
import com.ih2ome.common.utils.BeanMapUtil;
import com.ih2ome.common.utils.pingan.AESUtil;
import com.ih2ome.common.utils.pingan.HttpUtil;
import com.ih2ome.common.utils.pingan.HttpsUtil;
import com.ih2ome.common.utils.pingan.SignUtil;
import com.ih2ome.service.PinganApiService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;

/**
 * @author Sky
 * create 2018/07/26
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class PinganApiServiceImpl implements PinganApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PinganApiServiceImpl.class);

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
     * 4.1	获取门店支付方式列表
     *
     * @param pinganWxPayListRequestVO
     * @return
     */
    @Override
    public List<PinganWxPayListResponseVO> paylist(PinganWxPayListRequestVO pinganWxPayListRequestVO) throws PinganApiException {
        PinganWxRequestVO pinganWxRequestVO = getPinganWxRequestVO(pinganWxPayListRequestVO);
        String url = baseUrl + "paylist";
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
                    List<PinganWxPayListResponseVO> pinganWxPayListResponseVO = JSONObject.parseArray(decryptData, PinganWxPayListResponseVO.class);
                    return pinganWxPayListResponseVO;
                } else {
                    LOGGER.error("签名校验失败,签名信息:{}", pinganWxSignVerifyVO.getSign());
                    throw new PinganApiException("签名校验失败");
                }
            } else {
                LOGGER.info("无data数据,无需验签!");
            }
        } else {
            LOGGER.error("请求失败,失败原因:{}", pinganWxSignVerifyVO.toString());
            throw new PinganApiException("第三方请求失败:" + pinganWxSignVerifyVO.getMsg());
        }
        return null;
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
