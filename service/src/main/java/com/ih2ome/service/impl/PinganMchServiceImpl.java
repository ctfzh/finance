package com.ih2ome.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.Exception.PinganMchException;
import com.ih2ome.common.PageVO.PinganMchVO.*;
import com.ih2ome.common.PageVO.WebVO.WebBindCardCompanyReqVO;
import com.ih2ome.common.PageVO.WebVO.WebBindCardPersonalReqVO;
import com.ih2ome.common.utils.BeanMapUtil;
import com.ih2ome.common.utils.pingan.SerialNumUtil;
import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.model.lijiang.SubAccountCard;
import com.ih2ome.service.PinganMchService;
import com.pabank.sdk.PABankSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * @author Sky
 * create 2018/08/09
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class PinganMchServiceImpl implements PinganMchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PinganMchServiceImpl.class);


    //资金汇总账号
    @Value("${pingan.mch.mainAcctNo}")
    private String mainAcctNo;

    //文件传输用户短号
    @Value("${pingan.wxPay.uid}")
    private String uid;

    /**
     * 平安开通商户子账户
     *
     * @param userId
     * @return
     * @throws PinganMchException
     */
    @Override
    public PinganMchRegisterResVO registerAccount(Integer userId) throws PinganMchException, IOException {
        PinganMchRegisterReqVO pinganMchRegisterReqVO = new PinganMchRegisterReqVO();
        pinganMchRegisterReqVO.setCnsmrSeqNo(uid + SerialNumUtil.generateSerial());
        pinganMchRegisterReqVO.setFunctionFlag("1");
        pinganMchRegisterReqVO.setFundSummaryAcctNo(mainAcctNo);
        pinganMchRegisterReqVO.setTranNetMemberCode(userId.toString());
        pinganMchRegisterReqVO.setMemberProperty("00");
        //开通商户子账户请求数据报文
        String reqJson = JSONObject.toJSONString(pinganMchRegisterReqVO);
        LOGGER.info("registerAccount--->请求数据:{}", reqJson);
        Map<String, Object> result = PABankSDK.getInstance().apiInter(reqJson, "OpenCustAcctId");
        LOGGER.info("registerAccount--->响应数据:{}", JSONObject.toJSON(result));
        String code = (String) result.get("TxnReturnCode");
        if (!code.equals("000000")) {
            String txnReturnMsg = (String) result.get("TxnReturnMsg");
            LOGGER.error("registerAccount--->平安开通商户子账户失败,失败原因:{}", txnReturnMsg);
            throw new PinganMchException(txnReturnMsg);
        }
        PinganMchRegisterResVO resVO = BeanMapUtil.mapToObject(result, PinganMchRegisterResVO.class);
        return resVO;
    }


    /**
     * 绑定银行卡发送短信验证码(鉴权)
     *
     * @param subAccount
     * @param bankType
     * @param personalReqVO @throws PinganMchException
     * @throws IOException
     */
    @Override
    public void bindCardSendMessage(SubAccount subAccount, String bankType, WebBindCardPersonalReqVO personalReqVO) throws PinganMchException, IOException {
        PinganMchBindCardGetCodeReqVO reqVO = new PinganMchBindCardGetCodeReqVO();
        reqVO.setCnsmrSeqNo(uid + SerialNumUtil.generateSerial());
        reqVO.setFundSummaryAcctNo(mainAcctNo);
        reqVO.setSubAcctNo(subAccount.getAccount());
        reqVO.setTranNetMemberCode(subAccount.getUserId().toString());
        reqVO.setMemberName(personalReqVO.getUserName());
        reqVO.setMemberGlobalType("1");
        reqVO.setMemberGlobalId(personalReqVO.getIdCardNo());
        reqVO.setMemberAcctNo(personalReqVO.getBankCardNo());
        reqVO.setBankType(bankType);
        //其他银行，需要传递大小额行号和支行名称,超级网银号
        if ("2".equals(bankType)) {
            reqVO.setAcctOpenBranchName(personalReqVO.getBankName());
            reqVO.setCnapsBranchId(personalReqVO.getBankCnapsNo());
            reqVO.setEiconBankBranchId(personalReqVO.getBankSupNo());
        }
        reqVO.setMobile(personalReqVO.getMobile());
        //个人绑卡(短信验证)请求数据报文
        String reqJson = JSONObject.toJSONString(reqVO);
        LOGGER.info("bindCardSendMessage--->请求数据:{}", reqJson);
        Map<String, Object> result = PABankSDK.getInstance().apiInter(reqJson, "BindRelateAcctUnionPay");
        LOGGER.info("bindCardSendMessage--->响应数据:{}", JSONObject.toJSON(result));
        String code = (String) result.get("TxnReturnCode");
        if (!code.equals("000000")) {
            String txnReturnMsg = (String) result.get("TxnReturnMsg");
            LOGGER.error("bindCardSendMessage--->会员绑定提现账户(个人)-银联鉴权,失败原因:{}", txnReturnMsg);
            throw new PinganMchException(txnReturnMsg);
        }
    }

    /**
     * 绑定个人银行卡(短信校验)
     *
     * @param subAccount
     * @param reqVO
     */
    @Override
    public void bindPersonalCardVertify(SubAccount subAccount, WebBindCardPersonalReqVO reqVO) throws PinganMchException, IOException {
        PinganMchBindPersonalCardReqVO personalCardReqVO = new PinganMchBindPersonalCardReqVO();
        personalCardReqVO.setCnsmrSeqNo(uid + SerialNumUtil.generateSerial());
        personalCardReqVO.setFundSummaryAcctNo(mainAcctNo);
        personalCardReqVO.setTranNetMemberCode(subAccount.getUserId().toString());
        personalCardReqVO.setMemberAcctNo(reqVO.getBankCardNo());
        personalCardReqVO.setSubAcctNo(subAccount.getAccount());
        personalCardReqVO.setMessageCheckCode(reqVO.getMessageCode());
        //个人绑卡(短信验证码回填)请求数据报文
        String reqJson = JSONObject.toJSONString(personalCardReqVO);
        LOGGER.info("bindPersonalCardVertify--->请求数据:{}", reqJson);
        Map<String, Object> result = PABankSDK.getInstance().apiInter(reqJson, "BindRelateAccReUnionPay");
        LOGGER.info("bindPersonalCardVertify--->响应数据:{}", JSONObject.toJSON(result));
        String code = (String) result.get("TxnReturnCode");
        if (!code.equals("000000")) {
            String txnReturnMsg = (String) result.get("TxnReturnMsg");
            LOGGER.error("bindPersonalCardVertify--->会员绑定提现账户(个人)-回填验证码失败,失败原因:{}", txnReturnMsg);
            throw new PinganMchException(txnReturnMsg);
        }
    }

    /**
     * 绑定银行卡发送金额验证码(鉴权)
     *
     * @param subAccount
     * @param bankType
     * @param companyReqVO
     */
    @Override
    public void bindCardSendAmount(SubAccount subAccount, String bankType, WebBindCardCompanyReqVO companyReqVO) throws IOException, PinganMchException {
        PinganMchBindCardGetAmountReqVO reqVO = new PinganMchBindCardGetAmountReqVO();
        reqVO.setCnsmrSeqNo(uid + SerialNumUtil.generateSerial());
        reqVO.setFundSummaryAcctNo(mainAcctNo);
        reqVO.setSubAcctNo(subAccount.getAccount());
        reqVO.setTranNetMemberCode(subAccount.getUserId().toString());
        reqVO.setMemberName(companyReqVO.getUserName());
        reqVO.setMemberGlobalType("1");
        reqVO.setMemberGlobalId(companyReqVO.getIdCardNo());
        reqVO.setMemberAcctNo(companyReqVO.getBankCardNo());
        reqVO.setBankType(bankType);
        //其他银行，需要传递大小额行号和支行名称
        if ("2".equals(bankType)) {
            reqVO.setAcctOpenBranchName(companyReqVO.getBankName());
            reqVO.setCnapsBranchId(companyReqVO.getBankCnapsNo());
            reqVO.setEiconBankBranchId(companyReqVO.getBankSupNo());
        }
        reqVO.setMobile(companyReqVO.getMobile());
        //企业绑卡(金额验证)请求数据报文
        String reqJson = JSONObject.toJSONString(reqVO);
        LOGGER.info("bindCardSendAmount--->请求数据:{}", reqJson);
        Map<String, Object> result = PABankSDK.getInstance().apiInter(reqJson, "BindRelateAcctSmallAmount");
        LOGGER.info("bindCardSendAmount--->响应数据:{}", JSONObject.toJSON(result));
        String code = (String) result.get("TxnReturnCode");
        if (!code.equals("000000")) {
            String txnReturnMsg = (String) result.get("TxnReturnMsg");
            LOGGER.error("bindCardSendAmount--->会员绑定提现账户(企业)-银联鉴权,失败原因:{}", txnReturnMsg);
            throw new PinganMchException(txnReturnMsg);
        }

    }

    /**
     * 查询会员绑定信息(测试)
     *
     * @throws PinganMchException
     */
    @Override
    public void queryMemberBindInfo() throws PinganMchException, IOException {
        PinganMchQueryBindInfoReqVO bindInfoReqVO = new PinganMchQueryBindInfoReqVO();
        bindInfoReqVO.setCnsmrSeqNo(uid + SerialNumUtil.generateSerial());
        bindInfoReqVO.setFundSummaryAcctNo(mainAcctNo);
//        bindInfoReqVO.setSubAcctNo("4004000000001030");
        bindInfoReqVO.setQueryFlag("1");
        bindInfoReqVO.setPageNum("1");
        String reqJson = JSONObject.toJSONString(bindInfoReqVO);
        LOGGER.info("queryMemberBindInfo--->请求数据:{}", reqJson);
        Map<String, Object> result = PABankSDK.getInstance().apiInter(reqJson, "MemberBindQuery");
        LOGGER.info("queryMemberBindInfo--->响应数据:{}", JSONObject.toJSON(result));
        String code = (String) result.get("TxnReturnCode");
        if (!code.equals("000000")) {
            String txnReturnMsg = (String) result.get("TxnReturnMsg");
            LOGGER.error("queryMemberBindInfo--->会员绑定信息查询失败,失败原因:{}", txnReturnMsg);
            throw new PinganMchException(txnReturnMsg);
        }

    }

    /**
     * 查询小额鉴权转账结果（测试）
     *
     * @throws PinganMchException
     * @throws IOException
     */
    @Override
    public void queryTransferinfo() throws PinganMchException, IOException {
        PinganMchQueryTransferInfoReqVO transferInfoReqVO = new PinganMchQueryTransferInfoReqVO();
        transferInfoReqVO.setCnsmrSeqNo(uid + SerialNumUtil.generateSerial());
        transferInfoReqVO.setOldTranSeqNo("M394791808174423564712");
        transferInfoReqVO.setFundSummaryAcctNo(mainAcctNo);
        transferInfoReqVO.setTranDate("20180817");
        String reqJson = JSONObject.toJSONString(transferInfoReqVO);
        LOGGER.info("queryTransferinfo--->请求数据:{}", reqJson);
        Map<String, Object> result = PABankSDK.getInstance().apiInter(reqJson, "SmallAmountTransferQuery");
        LOGGER.info("queryTransferinfo--->响应数据:{}", JSONObject.toJSON(result));
        String code = (String) result.get("TxnReturnCode");
        if (!code.equals("000000")) {
            String txnReturnMsg = (String) result.get("TxnReturnMsg");
            LOGGER.error("queryTransferinfo--->小额鉴权转账查询失败,失败原因:{}", txnReturnMsg);
            throw new PinganMchException(txnReturnMsg);
        }
    }


    /**
     * 绑定企业银行卡(金额校验)
     *
     * @param subAccount
     * @param reqVO
     * @throws PinganMchException
     * @throws IOException
     */
    @Override
    public void bindCompanyCardVertify(SubAccount subAccount, WebBindCardCompanyReqVO reqVO) throws PinganMchException, IOException {
        PinganMchBindCompanyCardReqVO companyCardReqVO = new PinganMchBindCompanyCardReqVO();
        companyCardReqVO.setCnsmrSeqNo(uid + SerialNumUtil.generateSerial());
        companyCardReqVO.setFundSummaryAcctNo(mainAcctNo);
        companyCardReqVO.setTranNetMemberCode(subAccount.getUserId().toString());
        companyCardReqVO.setTakeCashAcctNo(reqVO.getBankCardNo());
        companyCardReqVO.setSubAcctNo(subAccount.getAccount());
        companyCardReqVO.setAuthAmt(reqVO.getVertifyAmount());
        //个人绑卡(短信验证码回填)请求数据报文
        String reqJson = JSONObject.toJSONString(companyCardReqVO);
        LOGGER.info("bindCompanyCardVertify--->请求数据:{}", reqJson);
        Map<String, Object> result = PABankSDK.getInstance().apiInter(reqJson, "CheckAmount");
        LOGGER.info("bindCompanyCardVertify--->响应数据:{}", JSONObject.toJSON(result));
        String code = (String) result.get("TxnReturnCode");
        if (!code.equals("000000")) {
            String txnReturnMsg = (String) result.get("TxnReturnMsg");
            LOGGER.error("bindCompanyCardVertify--->会员绑定提现账户(企业)-回填金额失败,失败原因:{}", txnReturnMsg);
            throw new PinganMchException(txnReturnMsg);
        }
    }

    /**
     * 查询子账户可用余额
     *
     * @param subAccount
     * @return
     * @throws PinganMchException
     * @throws IOException
     */
    @Override
    public PinganMchQueryBalanceResVO queryBalance(SubAccount subAccount) throws PinganMchException, IOException {
        PinganMchQueryBalanceReqVO queryBalanceReqVO = new PinganMchQueryBalanceReqVO();
        queryBalanceReqVO.setCnsmrSeqNo(uid + SerialNumUtil.generateSerial());
        queryBalanceReqVO.setFundSummaryAcctNo(mainAcctNo);
        queryBalanceReqVO.setSubAcctNo(subAccount.getAccount());
        //2：普通会员子账号 3：功能子账号
        queryBalanceReqVO.setQueryFlag("2");
        queryBalanceReqVO.setPageNum("1");
        String reqJson = JSONObject.toJSONString(queryBalanceReqVO);
        LOGGER.info("queryBalance--->请求数据:{}", reqJson);
        Map<String, Object> result = PABankSDK.getInstance().apiInter(reqJson, "CustAcctIdBalanceQuery");
        String resultJson = JSONObject.toJSONString(result);
        LOGGER.info("queryBalance--->响应数据:{}", resultJson);
        String code = (String) result.get("TxnReturnCode");
        if (!code.equals("000000")) {
            String txnReturnMsg = (String) result.get("TxnReturnMsg");
            LOGGER.error("queryBalance--->会员查询子账户余额,失败原因:{}", txnReturnMsg);
            throw new PinganMchException(txnReturnMsg);
        }
        PinganMchQueryBalanceResVO queryBalanceResVO = JSONObject.parseObject(resultJson, PinganMchQueryBalanceResVO.class);
        return queryBalanceResVO;
    }


    /**
     * 提现
     *
     * @param subAccount
     * @param subAccountCard
     * @param withdrawMoney
     * @param withdrawCharge
     * @throws PinganMchException
     * @throws IOException
     */
    @Override
    public String withDrawCash(SubAccount subAccount, SubAccountCard subAccountCard, Double withdrawMoney, Double withdrawCharge) throws PinganMchException, IOException {
        PinganMchWithDrawCashReqVO withDrawCashReqVO = new PinganMchWithDrawCashReqVO();
        withDrawCashReqVO.setFundSummaryAcctNo(mainAcctNo);
        withDrawCashReqVO.setCnsmrSeqNo(uid + SerialNumUtil.generateSerial());
        withDrawCashReqVO.setSubAcctNo(subAccount.getAccount());
        withDrawCashReqVO.setTranNetMemberCode(subAccount.getUserId().toString());
        withDrawCashReqVO.setSubAcctName("");
        withDrawCashReqVO.setTakeCashAcctNo(subAccountCard.getBankNo());
        withDrawCashReqVO.setTakeCashAcctName(subAccountCard.getIdCardName());
        withDrawCashReqVO.setCcy("RMB");
        withDrawCashReqVO.setCashAmt(String.valueOf(withdrawMoney));
        withDrawCashReqVO.setTakeCashCommission(String.valueOf(withdrawCharge));
        String reqJson = JSONObject.toJSONString(withDrawCashReqVO);
        LOGGER.info("withDrawCash--->请求数据:{}", reqJson);
        Map<String, Object> result = PABankSDK.getInstance().apiInter(reqJson, "MemberWithdrawCash");
        String resultJson = JSONObject.toJSONString(result);
        LOGGER.info("withDrawCash--->响应数据:{}", resultJson);
        String code = (String) result.get("TxnReturnCode");
        if (!code.equals("000000")) {
            String txnReturnMsg = (String) result.get("TxnReturnMsg");
            LOGGER.error("withDrawCash--->会员提现请求失败,失败原因:{}", txnReturnMsg);
            throw new PinganMchException(txnReturnMsg);
        }
        String cnsmrSeqNo = (String) result.get("CnsmrSeqNo");
        return cnsmrSeqNo;
    }


}
