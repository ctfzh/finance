package com.ih2ome.server.controller;

import com.ih2ome.common.PageVO.PinganWxConfigAddReqVO;
import com.ih2ome.common.PageVO.PinganWxConfigAddResVO;
import com.ih2ome.common.support.ResponseBodyVO;
import com.ih2ome.service.PinganConfigService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sky
 * create 2018/07/27
 * email sky.li@ixiaoshuidi.com
 **/
@Api(description = "商户配置")
@RestController
@RequestMapping("config")
public class PinganConfigController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigPaymentsController.class);

    @Autowired
    private PinganConfigService pinganConfigService;

    @GetMapping(value = "set", produces = "application/json;charset=UTF-8")
    public ResponseBodyVO setConfig() {
        PinganWxConfigAddReqVO pinganWxConfigAddReqVO = new PinganWxConfigAddReqVO();
        pinganWxConfigAddReqVO.setPmt_tag("WeixinOL");
//        pinganWxConfigAddReqVO.setSub_appid("wxed1a36ce3fa969f5");
        pinganWxConfigAddReqVO.setJsapi_path("https://fdwx.h2ome.cn/allinpay/confirm_wxpay/");
        PinganWxConfigAddResVO pinganWxConfigAddResVO = pinganConfigService.addConfig(pinganWxConfigAddReqVO);
        System.out.println(pinganWxConfigAddResVO);
        return ResponseBodyVO.generateResponseObject(0, null, "success");
    }

    @GetMapping(value = "get", produces = "application/json;charset=UTF-8")
    public ResponseBodyVO getConfig() {
        PinganWxConfigAddReqVO pinganWxConfigAddReqVO = new PinganWxConfigAddReqVO();
        pinganWxConfigAddReqVO.setPmt_tag("WeixinOL");
        pinganConfigService.getConfig(pinganWxConfigAddReqVO);
        return null;
    }
}
