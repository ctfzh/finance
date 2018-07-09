package com.ih2ome.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.PageVO.ConfigPaymentsVO;
import com.ih2ome.common.support.ResponseBodyVO;
import com.ih2ome.model.caspain.TerminalToken;
import com.ih2ome.service.ConfigPaymentsService;
import com.ih2ome.service.TerminalTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sky
 * create 2018/07/06
 * email sky.li@ixiaoshuidi.com
 **/
@Controller
@RequestMapping("configPayments")
@CrossOrigin
public class ConfigPaymentsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigPaymentsController.class);

    @Autowired
    private TerminalTokenService terminalTokenService;

    @Autowired
    private ConfigPaymentsService configPaymentsService;

    @GetMapping("get")
    public ResponseBodyVO getConfigPayments(HttpServletRequest request) {
        JSONObject data = new JSONObject();
        //获取请求头里的用户token
        String authorization = request.getHeader("Authorization");
        TerminalToken terminalToken = terminalTokenService.findByToken(authorization);
        if (terminalToken != null) {
            Integer userId = terminalToken.getUserId();
            LOGGER.info("用户token验证成功,token:{},userId:{}", authorization, userId);
            ConfigPaymentsVO configPaymentsVO = null;
            try {
                configPaymentsVO = configPaymentsService.findConfigPaymentsInfo(userId);
            } catch (Exception e) {
                e.printStackTrace();
                ResponseBodyVO.generateResponseObject(-1, null, e.getMessage());
            }
            data.put("configPaymentsVO", configPaymentsVO);
            ResponseBodyVO.generateResponseObject(0, data, "");
        }
        LOGGER.info("用户token验证失效,失效token:{}", authorization);
        return ResponseBodyVO.generateResponseObject(-1, null, "用户token已失效");
    }

}
