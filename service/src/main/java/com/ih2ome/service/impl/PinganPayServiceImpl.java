package com.ih2ome.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ih2ome.common.Exception.PinganWxPayException;
import com.ih2ome.common.PageVO.PinganWxPayVO.*;
import com.ih2ome.common.utils.BeanMapUtil;
import com.ih2ome.common.utils.pingan.AESUtil;
import com.ih2ome.common.utils.pingan.HttpUtil;
import com.ih2ome.common.utils.pingan.HttpsUtil;
import com.ih2ome.common.utils.pingan.SignUtil;
import com.ih2ome.service.PinganPayService;
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
public class PinganPayServiceImpl implements PinganPayService {

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
     * 4.1	获取门店支付方式列表
     *
     * @param pinganWxPayListReqVO
     * @return
     * @throws PinganWxPayException
     */
    @Override
    public List<PinganWxPayListResVO> paylist(PinganWxPayListReqVO pinganWxPayListReqVO) throws PinganWxPayException {
        //请求对象PinganWxRequestVO生成。
        PinganWxRequestVO pinganWxRequestVO = getPinganWxRequestVO(pinganWxPayListReqVO);
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
                    List<PinganWxPayListResVO> pinganWxPayListResVO = JSONObject.parseArray(decryptData, PinganWxPayListResVO.class);
                    return pinganWxPayListResVO;
                } else {
                    LOGGER.error("签名校验失败,签名信息:{}", pinganWxSignVerifyVO.getSign());
                    throw new PinganWxPayException("签名校验失败");
                }
            } else {
                LOGGER.info("无data数据,无需验签!");
            }
        } else {
            LOGGER.error("请求失败,失败原因:{}", pinganWxSignVerifyVO.toString());
            throw new PinganWxPayException("第三方请求失败:" + pinganWxSignVerifyVO.getMsg());
        }
        return null;
    }

    /**
     * 获取门店订单列表
     *
     * @param pinganWxOrderReqVO
     * @return
     * @throws PinganWxPayException
     */
    @Override
    public PinganWxOrderResVO queryOrderList(PinganWxOrderReqVO pinganWxOrderReqVO) throws PinganWxPayException {
        //请求对象PinganWxRequestVO生成。
        PinganWxRequestVO pinganWxRequestVO = getPinganWxRequestVO(pinganWxOrderReqVO);
        System.out.println(pinganWxRequestVO);
        String url = baseUrl + "order";
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
                    PinganWxOrderResVO pinganWxOrderResVO = JSONObject.parseObject(decryptData, new TypeReference<PinganWxOrderResVO>() {
                    });
                    return pinganWxOrderResVO;
                } else {
                    LOGGER.error("签名校验失败,签名信息:{}", pinganWxSignVerifyVO.getSign());
                    throw new PinganWxPayException("签名校验失败");
                }
            } else {
                LOGGER.info("无data数据,无需验签!");
            }
        } else {
            LOGGER.error("请求失败,失败原因:{}", pinganWxSignVerifyVO.toString());
            throw new PinganWxPayException("第三方请求失败:" + pinganWxSignVerifyVO.getMsg());
        }
        return null;
    }

    /**
     * 查询订单明细
     *
     * @param pinganWxOrderViewReqVO
     * @return
     * @throws PinganWxPayException
     */
    @Override
    public PinganWxOrderViewResVO queryOrderView(PinganWxOrderViewReqVO pinganWxOrderViewReqVO) throws PinganWxPayException {
        //请求对象PinganWxRequestVO生成。
        PinganWxRequestVO pinganWxRequestVO = getPinganWxRequestVO(pinganWxOrderViewReqVO);
        String url = baseUrl + "order/view";
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
                    PinganWxOrderViewResVO pinganWxOrderViewResVO = JSONObject.parseObject(decryptData, PinganWxOrderViewResVO.class);
                    LOGGER.info("queryOrderView-->请求参数:{}|返回参数:{}", JSONObject.toJSONString(pinganWxOrderViewReqVO), JSONObject.toJSONString(pinganWxOrderViewResVO));
                    return pinganWxOrderViewResVO;
                } else {
                    LOGGER.error("签名校验失败,签名信息:{}", pinganWxSignVerifyVO.getSign());
                    throw new PinganWxPayException("签名校验失败");
                }
            } else {
                LOGGER.info("无data数据,无需验签!");
            }
        } else {
            LOGGER.error("请求失败,失败原因:{}", pinganWxSignVerifyVO.toString());
            throw new PinganWxPayException("第三方请求失败:" + pinganWxSignVerifyVO.getMsg());
        }
        return null;
    }


    /**
     * 下订单
     *
     * @param pinganWxPayOrderReqVO
     * @return
     * @throws PinganWxPayException
     */
    @Override
    public PinganWxPayOrderResVO payOrder(PinganWxPayOrderReqVO pinganWxPayOrderReqVO) throws PinganWxPayException {
        //请求对象PinganWxRequestVO生成。
        PinganWxRequestVO pinganWxRequestVO = getPinganWxRequestVO(pinganWxPayOrderReqVO);
        String url = baseUrl + "payorder";
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
                    PinganWxPayOrderResVO pinganWxPayOrderResVO = JSONObject.parseObject(decryptData, PinganWxPayOrderResVO.class);
                    JSONObject jsonObject = JSONObject.parseObject(decryptData);
                    pinganWxPayOrderResVO.setPackage_info(jsonObject.getString("package"));
                    LOGGER.info("payOrder-->请求参数:{} | 返回参数:{}", JSONObject.toJSONString(pinganWxPayOrderReqVO), JSONObject.toJSONString(pinganWxPayOrderResVO));
                    return pinganWxPayOrderResVO;
                } else {
                    LOGGER.error("签名校验失败,签名信息:{}", pinganWxSignVerifyVO.getSign());
                    throw new PinganWxPayException("签名校验失败,请求参数:" + pinganWxRequestVO.toString() + ",签名信息:" + pinganWxSignVerifyVO.getSign());
                }
            } else {
                LOGGER.info("无data数据,无需验签!");
            }
        } else {
            LOGGER.error("请求失败,失败原因:{}", pinganWxSignVerifyVO.toString());
            throw new PinganWxPayException("平安第三方下单失败,失败原因:" + pinganWxSignVerifyVO.getMsg() + ",请求参数:" + pinganWxRequestVO.toString());
        }
        return null;

    }


    //请求数据处理(data数据AES加密,签名生成。)
    private PinganWxRequestVO getPinganWxRequestVO(Object object) {
        System.out.println(JSONObject.toJSONString(object));
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
