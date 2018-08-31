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
import com.ih2ome.dao.lijiang.SubAccountDao;
import com.ih2ome.model.caspain.LandlordBankCard;
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
import java.text.Collator;
import java.util.*;

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
    private PinganMchServiceImpl pinganMchService;
    @Autowired
    private SubAccountService subAccountService;
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
    @Autowired
    private WebPaymentsService webPaymentsService;


    @GetMapping(value = "userType/{userId}", produces = "application/json;charset=UTF-8")
    @ApiOperation("判断用户类型")
    public ResponseBodyVO getUserType(@ApiParam("用户登录id") @PathVariable("userId") Integer userId) {
        JSONObject data = new JSONObject();
        try {
            Integer landlordId = userService.findLandlordId(userId);
            Boolean bool = paymentsUserService.judgeUserType(landlordId);
            data.put("userType", bool ? 1 : 0);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("getUserType--->用户查询失败,用户id:{},失败原因:{}", userId, e.getMessage());
            return new ResponseBodyVO(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "查询成功");
    }


    @GetMapping(value = "show/openbutton/{userId}", produces = "application/json;charset=UTF-8")
    @ApiOperation("开通子账户按钮")
    public ResponseBodyVO showOpenButtons(@ApiParam("用户登录id") @PathVariable("userId") Integer userId) {
        JSONObject data = new JSONObject();
        try {
            //判断登录账号是主账号还是子账号，若是子账号则查询出对应的主账号
            Integer landlordId = userService.findLandlordId(userId);
            Integer flag = -1;
            //主账号
            if (landlordId.equals(userId)) {
                Boolean bool = paymentsUserService.judgeUserType(landlordId);
                //flag = bool ? 0 : -1;
                if (bool) {
                    //是否开通账户并绑卡
                    SubAccountCard subAccountCard = subAccountCardService.findSubaccountByLandlordId(landlordId);
                    flag = (subAccountCard == null ? 0 : -1);
                }
            }
            data.put("status", flag);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("showOpenButtons--->开通子账户按钮查询失败,用户id:{},失败原因:{}", userId, e.getMessage());
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
            SubAccount subAccount = subAccountService.findAccountByUserId(landlordId);
            if (subAccount != null) {
                return ResponseBodyVO.generateResponseObject(0, null, "该用户已开通子账号");
            }
            PinganMchRegisterResVO resVO = pinganMchService.registerAccount(userId);
            String subAcctNo = resVO.getSubAcctNo();
            WebRegisterResVO registerResVO = subAccountService.registerAccount(userId, subAcctNo);
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
        //根据银行名称首字母a-z排序
        Collator collator = Collator.getInstance(java.util.Locale.CHINA);
        banktypes.sort((bankType1, bankType2) -> {
            return collator.compare(bankType1.getBankname(), bankType2.getBankname());
        });
        data.put("banktypes", banktypes);
        return ResponseBodyVO.generateResponseObject(0, data, "获取银行类别成功");
    }

    @GetMapping(value = "bank/type/search", produces = "application/json;charset=UTF-8")
    @ApiOperation("获取银行类别(模糊搜索)")
    public ResponseBodyVO getBankType(@ApiParam("银行名称") @RequestParam(value = "bankName", required = false) String bankName) {
        JSONObject data = new JSONObject();
        //根据输入名称搜索银行类别
        List<ZjjzCnapsBanktype> banktypes = banktypeService.getBankType(bankName);
        //根据银行名称首字母a-z排序
        Collator collator = Collator.getInstance(java.util.Locale.CHINA);
        banktypes.sort((bankType1, bankType2) -> {
            return collator.compare(bankType1.getBankname(), bankType2.getBankname());
        });
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
        if (StringUtils.isBlank(bankCode) || StringUtils.isBlank(cityCode)) {
            return ResponseBodyVO.generateResponseObject(-1, null, "请求参数错误");
        }
        JSONObject data = new JSONObject();
        List<WebSearchCnapsVO> cnaps = bankinfoService.searchCnaps(bankCode, cityCode, bankName);
        //对银行进行a-z排序
//        Collections.sort(cnaps, Comparator.comparing(WebSearchCnapsVO::getCnapsName));
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
            SubAccount subAccount = subAccountService.findAccountByUserId(reqVO.getUserId());
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
            SubAccount subAccount = subAccountService.findAccountByUserId(reqVO.getUserId());
            //回填平安短信验证码
            pinganMchService.bindPersonalCardVertify(subAccount, reqVO);
            //回填验证成功，将银行卡信息存入数据库
            subAccountCardService.insertPersonalCardInfo(subAccount, reqVO);
            //插入新的银行卡信息到landlord_bankcard表
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
            SubAccount subAccount = subAccountService.findAccountByUserId(reqVO.getUserId());
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
            SubAccount subAccount = subAccountService.findAccountByUserId(reqVO.getUserId());
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


    @GetMapping(value = "bankcard/info/{userId}", produces = "application/json;charset=UTF-8")
    @ApiOperation("根据登录用户id查询绑定银行卡信息")
    public ResponseBodyVO getTMoneyBankInfo(@ApiParam("登录id") @PathVariable("userId") Integer userId) {
        JSONObject data = new JSONObject();
        try {
            //判断是主账号还是子账号,如果是子账号则查询出主账号
            Integer landlordId = userService.findLandlordId(userId);
            Integer userType = paymentsUserService.judgeUserType(userId) ? 1 : 0;
            //根据用户id和类型获取绑定银行卡信息
            LandlordBankCard landlordBankCard = landlordBankCardService.findBankCardInfo(landlordId, userType);
            Map<String, Object> cardInfo = new HashMap<>();
            if (landlordBankCard != null) {
                cardInfo.put("bank_name", landlordBankCard.getBankName());
                cardInfo.put("owner_name", landlordBankCard.getOwnerName());
                cardInfo.put("branch_bank", landlordBankCard.getBranchBank());
                cardInfo.put("card_no", landlordBankCard.getCardNo());
            }
            data.put("card_info", cardInfo);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("getTMoneyBankInfo--->获取银行卡信息失败,用户id:{},失败原因:{}", userId, e.getMessage());
            return new ResponseBodyVO(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "获取成功");
    }


    @GetMapping(value = "bankcard/withdrawMoney", produces = "application/json;charset=UTF-8")
    @ApiOperation("用户平安提现")
    public ResponseBodyVO withdrawMoney(@ApiParam("登录Id") @RequestParam("userId") Integer userId,
                                        @ApiParam("提现金额") @RequestParam("money") Double money,
                                        @ApiParam("query查询,withdraw提现") @RequestParam("type") String type,
                                        @ApiParam("提现home_trade表的trade_id") @RequestParam(value = "tradeId", required = false) String tradeId) {
        JSONObject data = new JSONObject();
//        try {
        //判断提现账号是主账号还是子账号，若是子账号则查询出对应的主账号
        Integer landlordId = userService.findLandlordId(userId);
        //根据用户id获取会员子账号和交易网会员代码
        SubAccount subAccount = subAccountService.findAccountByUserId(landlordId);
//            PinganMchQueryBalanceResVO queryBalanceResVO = pinganMchService.queryBalance(subAccount);
//            PinganMchQueryBalanceAcctArray balanceAcct = queryBalanceResVO.getAcctArray().get(0);

        SubAccountCard subAccountCard = subAccountCardService.findSubAccountByAccountId(subAccount.getId());
        //提现手续费默认一笔5.0(优先从平安商户子账户扣除,手续费不够则扣除部分手续费)
        Double withdrawCharge = 5.0;
        //获取商户子账户可提现余额
//            String cashAmt = balanceAcct.getCashAmt();
        String cashAmt = "10.0";
        //可提现余额
        Double cashMoney = Double.valueOf(cashAmt);
        //处理提现金额和手续费,算出正确的提现金额和手续费
        Map<String, Double> moneyAndCharge = webPaymentsService.disposeMoneyAndCharge(money, cashMoney, withdrawCharge);
        //获取需要提现的金额
        Double withdrawMoney = moneyAndCharge.get("money");
        //获取提现的手续费用
        withdrawCharge = moneyAndCharge.get("charge");
        //                moneyAndCharge.put("availMoney", Double.valueOf(balanceAcct.getAcctAvailBal()));
        moneyAndCharge.put("availMoney", 12.0);
        data.put("moneyAndCharge", moneyAndCharge);
        if ("withdraw".equals(type)) {
            //平安提现
//                String serialNo = pinganMchService.withDrawCash(subAccount, subAccountCard, withdrawMoney, withdrawCharge);
            String serialNo = "M394791808090340767002";
            //提现记录保存到数据库
            subWithdrawRecordService.insertWithdrawRecord(userId, subAccount, subAccountCard, withdrawMoney, withdrawCharge, serialNo, tradeId);
            return ResponseBodyVO.generateResponseObject(0, data, "提现请求成功");
        } else if ("query".equals(type)) {
            return ResponseBodyVO.generateResponseObject(0, data, "查询成功");
        }
//        } catch (PinganMchException | IOException e) {
//            e.printStackTrace();
//            LOGGER.info("withdrawMoney-{}--->失败,用户id:{},金额:{},失败原因:{}", type, userId, money, e.getMessage());
//            return new ResponseBodyVO(-1, data, e.getMessage());
//        }
        return null;
    }


    @PostMapping(value = "refresh/withdraw/status", produces = "application/json;charset=UTF-8")
    @ApiOperation("进入账户页面刷新提现状态")
    public ResponseBodyVO refreshWithdrawStatus(@ApiParam("h2ome_trade_id集合") @RequestBody List<String> tradeIds) {
        JSONObject data = new JSONObject();
        Integer landlordId = 0;
        //根据主账号id查询所有的提现中的记录
//            List<SubWithdrawRecord> withdrawRecords = subWithdrawRecordService.queryWithdrawRecords(landlordId);
        //根据h2ome_trade表的trade_id查询提现状态
        List<SubWithdrawRecord> withdrawRecords = subWithdrawRecordService.queryWithdrawRecords(tradeIds);
        List<WebWithdrawStatusResVO> statusResVOS = new ArrayList<WebWithdrawStatusResVO>();
        for (SubWithdrawRecord withdrawRecord : withdrawRecords) {
            Integer withdrawStatus = withdrawRecord.getWithdrawStatus();
            WebWithdrawStatusResVO statusResVO = new WebWithdrawStatusResVO();
            statusResVO.setTradeId(withdrawRecord.getH2omeTradeId());
            //默认数据库的提现状态
            statusResVO.setWithdrawStatus(String.valueOf(withdrawStatus));
            String serialNo = withdrawRecord.getSerialNo();
            PinganMchQueryTranStatusResVO tranStatusResVO = null;
            if (withdrawStatus == 0) {
                try {
                    tranStatusResVO = pinganMchService.queryTranStatus(serialNo);
                } catch (PinganMchException | IOException e) {
                    e.printStackTrace();
                    LOGGER.info("refreshWithdrawStatus--->该笔提现状态失败,请求tradeId:{},失败原因:{}", withdrawRecord.getH2omeTradeId(), e.getMessage());
                }
            }
            if (tranStatusResVO != null) {
                String tranStatus = tranStatusResVO.getTranStatus();
                //0成功，1失败(平安)
                if ("0".equals(tranStatus)) {
                    //修改提现状态（1:成功）
                    withdrawRecord.setWithdrawStatus(1);
                    statusResVO.setWithdrawStatus("1");
                    subWithdrawRecordService.updateWithdrawStatus(withdrawRecord);
                } else if ("1".equals(tranStatus)) {
                    //修改提现状态(2:失败)
                    withdrawRecord.setWithdrawStatus(2);
                    statusResVO.setWithdrawStatus("2");
                    subWithdrawRecordService.updateWithdrawStatus(withdrawRecord);
                }
            }
            statusResVOS.add(statusResVO);
        }
        data.put("status", statusResVOS);
        return ResponseBodyVO.generateResponseObject(0, data, "提现状态刷新成功");
    }

    @GetMapping(value = "bindcard/unbind/{userId}", produces = "application/json;charset=UTF-8")
    @ApiOperation("会员子账户解绑银行卡")
    public ResponseBodyVO unbindBankCard(@ApiParam("登录Id") @PathVariable("userId") Integer userId) {
        JSONObject data = new JSONObject();
        //判断解绑账号是主账号还是子账号，若是子账号则查询出对应的主账号
        Integer landlordId = userService.findLandlordId(userId);
        try {
            if (landlordId.equals(userId)) {
                SubAccount subaccount = subAccountService.findAccountByUserId(landlordId);
                SubAccountCard subAccountCard = subAccountCardService.findSubaccountByLandlordId(landlordId);
                //平安银行卡解绑
                pinganMchService.unbindBankCard(subaccount, subAccountCard);
                //水滴数据库解绑
                subAccountCardService.unbindSubAccountCard(subAccountCard);
                landlordBankCardService.unbindLandlordBankCard(subAccountCard.getBankNo());
            } else {
                return ResponseBodyVO.generateResponseObject(-1, data, "您没有权限进行解绑操作");
            }
        } catch (PinganMchException | IOException e) {
            e.printStackTrace();
            LOGGER.info("unbindBankCard--->解绑银行卡失败,登录用户id:{},主账户id:{},失败原因:{}", userId, landlordId, e.getMessage());
            return new ResponseBodyVO(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "解绑成功");
    }

}
