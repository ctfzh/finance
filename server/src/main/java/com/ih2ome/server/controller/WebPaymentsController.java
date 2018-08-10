package com.ih2ome.server.controller;

import com.ih2ome.model.caspain.TerminalToken;
import com.ih2ome.service.TerminalTokenService;
import com.ih2ome.service.impl.PinganMchServiceImpl;
import io.swagger.annotations.Api;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sky
 * create 2018/08/08
 * email sky.li@ixiaoshuidi.com
 **/
@Api("web端支付账户页面")
@RestController
@RequestMapping("web/mch")
public class WebPaymentsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebPaymentsController.class);

    @Autowired
    private TerminalTokenService terminalTokenService;

    @Autowired
    private PinganMchServiceImpl pinganMchService;

    @RequestMapping("register")
    public Integer registerMerchant(@RequestHeader("Authorization") String authorization) {
        //获取用户id
        TerminalToken terminalToken = terminalTokenService.findByToken(authorization.split(" ")[1]);
        Integer userId = terminalToken.getUserId();
        
        return null;
    }

}


