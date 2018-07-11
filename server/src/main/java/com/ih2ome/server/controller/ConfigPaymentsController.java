package com.ih2ome.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.PageVO.ConfigPaymentsVO;
import com.ih2ome.common.support.ResponseBodyVO;
import com.ih2ome.model.caspain.TerminalToken;
import com.ih2ome.service.ConfigPaymentsService;
import com.ih2ome.service.TerminalTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Sky
 * create 2018/07/06
 * email sky.li@ixiaoshuidi.com
 **/
@Api(description = "支付配置接口")
@RestController
@RequestMapping("configPayments")
@CrossOrigin
public class ConfigPaymentsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigPaymentsController.class);

    @Autowired
    private TerminalTokenService terminalTokenService;

    @Autowired
    private ConfigPaymentsService configPaymentsService;

    @GetMapping(value = "get", produces = "application/json;charset=UTF-8")
    @ApiOperation("获取支付配置")
    public ResponseBodyVO getConfigPayments(HttpServletRequest request) {
        JSONObject data = new JSONObject();
        String authorization = request.getHeader("Authorization");
        TerminalToken terminalToken = terminalTokenService.findByToken(authorization);
        if (terminalToken != null) {
            Integer userId = terminalToken.getUserId();
            LOGGER.info("getConfigPayments-----登录成功token:{},userId:{}", authorization, userId);
            ConfigPaymentsVO configPaymentsVO = null;
            try {
                configPaymentsVO = configPaymentsService.findConfigPaymentsInfo(userId);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info("getConfigPayments-----findConfigPaymentsInfo()出错,错误:{}", e.getMessage());
                return ResponseBodyVO.generateResponseObject(-1, null, e.getMessage());
            }
            data.put("configPaymentsVO", configPaymentsVO);
            return ResponseBodyVO.generateResponseObject(0, data, "");
        }
        LOGGER.info("getConfigPayments-----验证失败,失败token:{}", authorization);
        return ResponseBodyVO.generateResponseObject(-1, null, "用户token验证失败");
    }

    @PostMapping(value = "set", produces = "application/json;charset=UTF-8")
    @ApiOperation("设置支付配置")
    public ResponseBodyVO setConfigPayments(@RequestHeader(value = "Authorization") String authorization,
                                            @ApiParam(value = "费用承担方(renter:租客,landlord:房东)") @RequestParam(value = "assumePerson") String assumePerson)

    {
        TerminalToken terminalToken = terminalTokenService.findByToken(authorization);
        if (terminalToken != null) {
            Integer userId = terminalToken.getUserId();
            LOGGER.info("setConfigPayments-----登录成功token:{},userId:{}", authorization, userId);
            try {
                configPaymentsService.setConfigPaymentsInfo(userId, assumePerson);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info("setConfigPayments-----setConfigPaymentsInfo({},{})出错,错误原因:{},", userId, assumePerson, e.getMessage());
                return ResponseBodyVO.generateResponseObject(-1, null, e.getMessage());
            }
            return ResponseBodyVO.generateResponseObject(0, null, "设置成功");
        }
        LOGGER.info("setConfigPayments-----验证失败,失败token:{}", authorization);
        return ResponseBodyVO.generateResponseObject(-1, null, "用户token验证失败");
    }
}