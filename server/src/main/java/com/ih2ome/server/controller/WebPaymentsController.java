package com.ih2ome.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.Exception.PinganMchException;
import com.ih2ome.common.Exception.WebPaymentsException;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchRegisterResVO;
import com.ih2ome.common.PageVO.WebVO.WebBindCardGetCodeReqVO;
import com.ih2ome.common.PageVO.WebVO.WebRegisterResVO;
import com.ih2ome.common.PageVO.WebVO.WebSearchCnapsVO;
import com.ih2ome.common.support.ResponseBodyVO;
import com.ih2ome.model.caspain.TerminalToken;
import com.ih2ome.model.lijiang.PubPayCity;
import com.ih2ome.model.lijiang.PubPayNode;
import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.model.lijiang.ZjjzCnapsBanktype;
import com.ih2ome.service.*;
import com.ih2ome.service.impl.PinganMchServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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
    @Autowired
    private ZjjzCnapsBanktypeService banktypeService;
    @Autowired
    private PubPayNodeService pubPayNodeService;
    @Autowired
    private PubPayCityService pubPayCityService;
    @Autowired
    private ZjjzCnapsBankinfoService bankinfoService;


    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ApiOperation("注册商户子账户")
    public ResponseBodyVO registerMerchant(@RequestHeader("Authorization") String authorization) {
        JSONObject data = new JSONObject();
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
            WebRegisterResVO registerResVO = webPaymentsService.registerAccount(userId, subAcctNo);
            data.put("registerResVO", registerResVO);
        } catch (PinganMchException | IOException e) {
            e.printStackTrace();
            LOGGER.info("registerMerchant--->注册商户子账号失败,用户id:{},失败原因:{}", userId, e.getMessage());
            return new ResponseBodyVO(-1, data, e.getMessage());
        } catch (WebPaymentsException e) {
            e.printStackTrace();
            LOGGER.info("registerMerchant--->添加商户子账号失败,用户id:{},失败原因:{}", userId, e.getMessage());
            return new ResponseBodyVO(-1, data, e.getMessage());
        }
        return new ResponseBodyVO(0, data, "注册成功");
    }

    @GetMapping(value = "bank/type", produces = "application/json;charset=UTF-8")
    @ApiOperation("获取银行类别")
    public ResponseBodyVO getBankType() {
        JSONObject data = new JSONObject();
        List<ZjjzCnapsBanktype> banktypes = banktypeService.getBankType();
        data.put("banktypes", banktypes);
        return ResponseBodyVO.generateResponseObject(0, data, "获取银行类别成功");
    }

    @GetMapping(value = "province", produces = "application/json;charset=UTF-8")
    @ApiOperation("获取省份")
    public ResponseBodyVO getProvince() {
        JSONObject data = new JSONObject();
        List<PubPayNode> provinces = pubPayNodeService.getProvinces();
        data.put("provinces", provinces);
        return ResponseBodyVO.generateResponseObject(0, data, "获取省份成功");
    }

    @GetMapping(value = "city/{province}", produces = "application/json;charset=UTF-8")
    @ApiOperation("根据省份获取市")
    public ResponseBodyVO getCity(@ApiParam(value = "省code") @PathVariable("province") String province) {
        JSONObject data = new JSONObject();
        List<PubPayCity> cities = pubPayCityService.getCitiesByProvince(province);
        data.put("cities", cities);
        return ResponseBodyVO.generateResponseObject(0, data, "获取城市成功");
    }

    @GetMapping(value = "district/{city}", produces = "application/json;charset=UTF-8")
    @ApiOperation("根据市获取县、区")
    public ResponseBodyVO getDistrict(@ApiParam(value = "市code") @PathVariable("city") String city) {
        JSONObject data = new JSONObject();
        List<PubPayCity> districts = pubPayCityService.getDistrictsByCity(city);
        data.put("districts", districts);
        return ResponseBodyVO.generateResponseObject(0, data, "获取区/县成功");
    }

    @GetMapping(value = "search/cnaps", produces = "application/json;charset=UTF-8")
    @ApiOperation("获取大小额银联号")
    public ResponseBodyVO searchCnaps(@ApiParam("银行类别code") @RequestParam("bankCode") String bankCode,
                                      @ApiParam("市或者(区、县)code") @RequestParam("cityCode") String cityCode,
                                      @ApiParam("支行名称") @RequestParam(value = "bankName", required = false) String bankName) {
        if (bankCode == null || cityCode == null) {
            return ResponseBodyVO.generateResponseObject(-1, null, "请求参数错误");
        }
        JSONObject data = new JSONObject();
        List<WebSearchCnapsVO> cnaps = bankinfoService.searchCnaps(bankCode, cityCode, bankName);
        data.put("cnaps", cnaps);
        return ResponseBodyVO.generateResponseObject(0, data, "获取大小额银联号成功");
    }

    @PostMapping(value = "bindCard/code", produces = "application/json;charset=UTF-8")
    @ApiOperation("个人账户绑定发送短信验证码")
    public ResponseBodyVO sendMessage(@RequestBody @Valid WebBindCardGetCodeReqVO codeReqVO, BindingResult bindingResult) {
        JSONObject data = new JSONObject();
        if (bindingResult.hasErrors()) {
            return ResponseBodyVO.generateResponseObject(-1, data, "请求参数错误");
        }
        try {
            //根据用户id获取会员子账号和交易网会员代码
            SubAccount subAccount = webPaymentsService.findAccountByUserId(codeReqVO.getUserId());
            //判断银行是否是平安银行
            String bankType = bankinfoService.judgeBankTypeIsPingan(codeReqVO.getBankCnapsNo());
            //发送短信鉴权
            pinganMchService.bindCardSendMessage(subAccount, bankType, codeReqVO);
        } catch (PinganMchException | IOException e) {
            e.printStackTrace();
            LOGGER.info("sendMessage--->绑卡发送短信验证码失败,请求数据:{},失败原因:{}", codeReqVO.toString(), e.getMessage());
            return new ResponseBodyVO(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "鉴权成功,成功发送短信");
    }
}


