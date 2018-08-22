package com.ih2ome.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.Exception.PinganMchException;
import com.ih2ome.common.Exception.WebPaymentsException;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchQueryBalanceAcctArray;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchQueryBalanceResVO;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchQueryTranStatusResVO;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchRegisterResVO;
import com.ih2ome.common.PageVO.WebVO.*;
import com.ih2ome.common.support.ResponseBodyVO;
import com.ih2ome.model.caspain.TerminalToken;
import com.ih2ome.model.lijiang.*;
import com.ih2ome.service.*;
import com.ih2ome.service.impl.PinganMchServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private SubAccountCardService subAccountCardService;
    @Autowired
    private LandlordBankCardService landlordBankCardService;
    @Autowired
    private UserService userService;
    @Autowired
    private SubWithdrawRecordService subWithdrawRecordService;
    @Autowired
    private ConfigPaymentsUserService paymentsUserService;

    @GetMapping(value = "show/openbutton/{userId}", produces = "application/json;charset=UTF-8")
    @ApiOperation("开通子账户按钮")
    public ResponseBodyVO getUserType(@ApiParam("用户登录id") @PathVariable("userId") Integer userId) {
        JSONObject data = new JSONObject();
        try {
            //判断登录账号是主账号还是子账号，若是子账号则查询出对应的主账号
            Integer landlordId = userService.findLandlordId(userId);
            Integer flag = -1;
            //主账号
            if (landlordId.equals(userId)) {
                Boolean bool = paymentsUserService.judgeUserType(landlordId);
                //            flag = bool ? 0 : -1;
                if (bool) {
                    //是否开通账户并绑卡
                    SubAccountCard subAccountCard = subAccountCardService.findSubaccountByLandlordId(landlordId);
                    flag = (subAccountCard == null ? 0 : -1);
                }
            }
            data.put("status", flag);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("registerMerchant--->开通子账户按钮查询失败,用户id:{},失败原因:{}", userId, e.getMessage());
            return new ResponseBodyVO(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "查询成功");
    }

    @RequestMapping(value = "register/{userId}", method = RequestMethod.GET)
    @ApiOperation("注册商户子账户")
    public ResponseBodyVO registerMerchant(@ApiParam("用户登录id") @PathVariable("userId") Integer userId) {
        JSONObject data = new JSONObject();
        //获取用户id
//        TerminalToken terminalToken = terminalTokenService.findByToken(authorization.split(" ")[1]);
//        Integer userId = terminalToken.getUserId();
        Integer landlordId = userService.findLandlordId(userId);
        try {
            //判断是否有商户子账号
            SubAccount subAccount = webPaymentsService.findAccountByUserId(landlordId);
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

    @PostMapping(value = "personal/bindCard/code", produces = "application/json;charset=UTF-8")
    @ApiOperation("个人账户绑定发送短信验证码")
    public ResponseBodyVO sendMessage(@RequestBody @Valid WebBindCardPersonalReqVO reqVO, BindingResult bindingResult) {
        JSONObject data = new JSONObject();
        if (bindingResult.hasErrors()) {
            return ResponseBodyVO.generateResponseObject(-1, data, "请求参数错误");
        }
        try {
            //根据用户id获取会员子账号和交易网会员代码
            SubAccount subAccount = webPaymentsService.findAccountByUserId(reqVO.getUserId());
            //判断银行是否是平安银行
            String bankType = bankinfoService.judgeBankTypeIsPingan(reqVO.getBankCnapsNo());
            //发送短信鉴权
            pinganMchService.bindCardSendMessage(subAccount, bankType, reqVO);
        } catch (PinganMchException | IOException e) {
            e.printStackTrace();
            LOGGER.info("sendMessage--->绑卡发送短信验证码失败,请求数据:{},失败原因:{}", reqVO.toString(), e.getMessage());
            return new ResponseBodyVO(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "鉴权成功,成功发送短信");
    }

    @PostMapping(value = "personal/bindCard/submit", produces = "application/json;charset=UTF-8")
    @ApiOperation("个人账户绑定银行卡提交")
    public ResponseBodyVO submitPersonalBindInfo(@RequestBody @Valid WebBindCardPersonalReqVO reqVO, BindingResult bindingResult) {
        JSONObject data = new JSONObject();
        if (bindingResult.hasErrors() || StringUtils.isBlank(reqVO.getMessageCode())) {
            return ResponseBodyVO.generateResponseObject(-1, data, "请求参数错误");
        }
        try {
            //根据用户id获取会员子账号和交易网会员代码
            SubAccount subAccount = webPaymentsService.findAccountByUserId(reqVO.getUserId());
            //回填平安短信验证码
            pinganMchService.bindPersonalCardVertify(subAccount, reqVO);
            //回填验证成功，将银行卡信息存入数据库
            subAccountCardService.insertPersonalCardInfo(subAccount, reqVO);
            landlordBankCardService.insertPersonalCardInfo(subAccount, reqVO);
        } catch (PinganMchException | IOException e) {
            e.printStackTrace();
            LOGGER.info("submitPersonalBindInfo--->绑卡信息提交失败,请求数据:{},失败原因:{}", reqVO.toString(), e.getMessage());
            return new ResponseBodyVO(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "绑定成功");
    }


    @PostMapping(value = "company/bindCard/amount", produces = "application/json;charset=UTF-8")
    @ApiOperation("企业账户绑定发送金额验证码")
    public ResponseBodyVO sendAmount(@RequestBody @Valid WebBindCardCompanyReqVO reqVO, BindingResult bindingResult) {
        JSONObject data = new JSONObject();
        if (bindingResult.hasErrors()) {
            return ResponseBodyVO.generateResponseObject(-1, data, "请求参数错误");
        }
        try {
            //根据用户id获取会员子账号和交易网会员代码
            SubAccount subAccount = webPaymentsService.findAccountByUserId(reqVO.getUserId());
            //判断银行是否是平安银行
            String bankType = bankinfoService.judgeBankTypeIsPingan(reqVO.getBankCnapsNo());
            //发送金额鉴权
            pinganMchService.bindCardSendAmount(subAccount, bankType, reqVO);
        } catch (PinganMchException | IOException e) {
            e.printStackTrace();
            LOGGER.info("sendAmount--->绑卡发送金额验证码失败,请求数据:{},失败原因:{}", reqVO.toString(), e.getMessage());
            return new ResponseBodyVO(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "鉴权成功,成功发送金额");
    }


    @PostMapping(value = "company/bindCard/submit", produces = "application/json;charset=UTF-8")
    @ApiOperation("企业账户绑定银行卡提交")
    public ResponseBodyVO submitCompanyBindInfo(@RequestBody @Valid WebBindCardCompanyReqVO reqVO, BindingResult bindingResult) {
        JSONObject data = new JSONObject();
        if (bindingResult.hasErrors() || StringUtils.isBlank(reqVO.getVertifyAmount())) {
            return ResponseBodyVO.generateResponseObject(-1, data, "请求参数错误");
        }
        try {
            //根据用户id获取会员子账号和交易网会员代码
            SubAccount subAccount = webPaymentsService.findAccountByUserId(reqVO.getUserId());
            //回填平安小额鉴权金额
            pinganMchService.bindCompanyCardVertify(subAccount, reqVO);
            //回填验证成功，将银行卡信息存入数据库
            subAccountCardService.insertCompanyCardInfo(subAccount, reqVO);
            landlordBankCardService.insertCompanyCardInfo(subAccount, reqVO);
        } catch (PinganMchException | IOException e) {
            e.printStackTrace();
            LOGGER.info("submitCompanyBindInfo--->绑卡信息提交失败,请求数据:{},失败原因:{}", reqVO.toString(), e.getMessage());
            return new ResponseBodyVO(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "绑定成功");
    }


//    @GetMapping(value = "bankcard/info/{userid}", produces = "application/json;charset=UTF-8")
//    @ApiOperation("根据登录用户id查询绑定银行卡信息")
//    public ResponseBodyVO getTMoneyBankInfo(@ApiParam("登录id") @PathVariable("userId") Integer userId) {
//        JSONObject data = new JSONObject();
//        //判断是主账号还是子账号,
//        //根据用户查询会员子账号信息
//        SubAccount subAccount = webPaymentsService.findAccountByUserId(userId);
//        Integer accountId = subAccount.getId();
//        //根据会员子账号主键查询绑定银行卡信息
//        SubAccountCard subAccountCard = subAccountCardService.findSubAccountByAccountId(accountId);
//        WebTMoneyBankCardInfoVO bankCardInfo = new WebTMoneyBankCardInfoVO();
//        bankCardInfo.setBankName(subAccountCard.getBankName());
//        bankCardInfo.setBankNo(subAccountCard.getBankNo());
//        return ResponseBodyVO.generateResponseObject(0, null, "成功");
//    }


    @GetMapping(value = "bankcard/withdrawMoney/{userId}/{money}", produces = "application/json;charset=UTF-8")
    @ApiOperation("用户平安提现")
    public ResponseBodyVO withdrawMoney(@ApiParam("登录Id") @PathVariable("userId") Integer userId,
                                        @ApiParam("提现金额") @PathVariable("money") String money) {
        JSONObject data = new JSONObject();
        try {
            //判断提现账号是主账号还是子账号，若是子账号则查询出对应的主账号
            Integer landlordId = userService.findLandlordId(userId);
            //根据用户id获取会员子账号和交易网会员代码
            SubAccount subAccount = webPaymentsService.findAccountByUserId(landlordId);
            PinganMchQueryBalanceResVO queryBalanceResVO = pinganMchService.queryBalance(subAccount);
            PinganMchQueryBalanceAcctArray balanceAcct = queryBalanceResVO.getAcctArray().get(0);
            SubAccountCard subAccountCard = subAccountCardService.findSubAccountByAccountId(subAccount.getId());
            //提现手续费默认一笔5.0
            Double withdrawCharge = 0.0;
            String cashAmt = balanceAcct.getCashAmt();
            Double withdrawMoney = 0.0;
            Double initMoney = Double.valueOf(money);
            Double cashMoney = Double.valueOf(cashAmt);
            if (initMoney > (cashMoney - withdrawCharge)) {
                withdrawMoney = Double.valueOf(cashAmt);
            } else {
                withdrawMoney = Double.valueOf(money);
            }
            //平安提现
            String serialNo = pinganMchService.withDrawCash(subAccount, subAccountCard, withdrawMoney, withdrawCharge);
            //提现记录保存到数据库
            subWithdrawRecordService.insertWithdrawRecord(userId, subAccount, subAccountCard, withdrawMoney, withdrawCharge, serialNo);
        } catch (PinganMchException | IOException e) {
            e.printStackTrace();
            LOGGER.info("withdrawMoney--->提现请求失败,用户id:{},提现金额:{},失败原因:{}", userId, money, e.getMessage());
            return new ResponseBodyVO(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "提现请求成功");
    }


    @GetMapping(value = "refresh/withdraw/status", produces = "application/json;charset=UTF-8")
    @ApiOperation("进入账户页面刷新提现状态")
    public ResponseBodyVO refreshWithdrawStatus(@ApiParam("登录Id") @PathVariable("userId") Integer userId) {
        JSONObject data = new JSONObject();
        Integer landlordId = 0;
        try {
            //判断提现账号是主账号还是子账号，若是子账号则查询出对应的主账号
            landlordId = userService.findLandlordId(userId);
            //根据主账号id查询所有的提现中的记录
            List<SubWithdrawRecord> withdrawRecords = subWithdrawRecordService.queryWithdrawRecords(landlordId);
            for (SubWithdrawRecord withdrawRecord : withdrawRecords) {
                String serialNo = withdrawRecord.getSerialNo();
                PinganMchQueryTranStatusResVO tranStatusResVO = pinganMchService.queryTranStatus(serialNo);
                String tranStatus = tranStatusResVO.getTranStatus();
                //0成功，1失败(平安)
                if ("0".equals(tranStatus)) {
                    //修改提现状态（1:成功）
                    withdrawRecord.setWithdrawStatus(1);
                    subWithdrawRecordService.updateWithdrawStatus(withdrawRecord);
                } else if ("1".equals(tranStatus)) {
                    //修改提现状态(2:失败)
                    withdrawRecord.setWithdrawStatus(2);
                    subWithdrawRecordService.updateWithdrawStatus(withdrawRecord);
                }
            }
        } catch (PinganMchException | IOException e) {
            e.printStackTrace();
            LOGGER.info("refreshWithdrawStatus--->刷新提现状态失败,登录用户id:{},主账户id:{},失败原因:{}", userId, landlordId, e.getMessage());
            return new ResponseBodyVO(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "提现状态刷新成功");
    }


}


