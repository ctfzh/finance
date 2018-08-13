package com.ih2ome.server.controller;

import com.ih2ome.common.Exception.PinganMchException;
import com.ih2ome.common.Exception.WebPaymentsException;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchRegisterReqVO;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchRegisterResVO;
import com.ih2ome.common.support.ResponseBodyVO;
import com.ih2ome.common.utils.pingan.SerialNumUtil;
import com.ih2ome.model.caspain.TerminalToken;
import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.service.TerminalTokenService;
import com.ih2ome.service.WebPaymentsService;
import com.ih2ome.service.impl.PinganMchServiceImpl;
import io.swagger.annotations.Api;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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

    @Autowired
    private WebPaymentsService webPaymentsService;


    @RequestMapping(value = "register", method = RequestMethod.POST)

    public ResponseBodyVO registerMerchant(@RequestHeader("Authorization") String authorization) {
        //获取用户id
        TerminalToken terminalToken = terminalTokenService.findByToken(authorization.split(" ")[1]);
        Integer userId = terminalToken.getUserId();
        try {
            //判断是否有商户子账号
            SubAccount subAccount = webPaymentsService.findAccountByUserId(userId);
            if (subAccount != null) {
                return ResponseBodyVO.generateResponseObject(0, null, "该用户已开通子账号");
            }
            PinganMchRegisterResVO resVO = pinganMchService.registerAccount(userId);
            String subAcctNo = resVO.getSubAcctNo();
            webPaymentsService.registerAccount(userId, subAcctNo);
        } catch (PinganMchException | IOException e) {
            e.printStackTrace();
            LOGGER.info("registerMerchant--->注册商户子账号失败,用户id:{},失败原因:{}", userId, e.getMessage());
            return new ResponseBodyVO(-1, null, e.getMessage());
        } catch (WebPaymentsException e) {
            e.printStackTrace();
            LOGGER.info("registerMerchant--->添加商户子账号失败,用户id:{},失败原因:{}", userId, e.getMessage());
            return new ResponseBodyVO(-1, null, e.getMessage());
        }
        return new ResponseBodyVO(0, null, "注册成功");
    }


}


