package com.ih2ome.common.utils.sms;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.utils.client.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sky
 * create 2018/04/09
 * email sky.li@ixiaoshuidi.com
 **/
public class SmsUtil {
    //发送短信模板
    public static boolean sendTemplateText(String baseUrl, String templateCode
            , String phoneNumber, JSONObject data, Integer interval) {
        JSONObject req = new JSONObject();
        req.put("number", phoneNumber);
        req.put("template", templateCode);
        req.put("data", data);
        req.put("interval", interval);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String result = HttpClientUtil.doPostJson(baseUrl + "/text", JSONObject.toJSONString(req), headers);
        JSONObject res = JSONObject.parseObject(result);
        return res.getString("retcode").equals("0");
    }

    //发送短信验证码
    public static boolean sendCode(String baseUrl, String number, Integer interval) {
        JSONObject req = new JSONObject();
        //手机号
        req.put("number", number);
        //发送间隔
        req.put("interval", interval);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String result = HttpClientUtil.doPostJson(baseUrl + "/code", JSONObject.toJSONString(req), headers);
        System.out.println(result);
        JSONObject res = JSONObject.parseObject(result);
        return res.getString("retcode").equals("0");
    }

    //验证验证码
    public static boolean validateCode(String baseUrl, String number, String code) {
        Map<String, Object> params = new HashMap<>();
        //手机号
        params.put("number", number);
        //验证码
        params.put("code", code);
        String result = HttpClientUtil.doGet(baseUrl + "/code", params);
        System.out.println(result);
        JSONObject res = JSONObject.parseObject(result);
        return res.getString("retcode").equals("0");
    }
}
