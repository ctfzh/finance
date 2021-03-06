package com.ih2ome.service;

import com.ih2ome.common.Exception.PinganMchException;
import com.ih2ome.common.PageVO.PinganMchVO.*;
import com.ih2ome.common.PageVO.WebVO.WebBindCardCompanyReqVO;
import com.ih2ome.common.PageVO.WebVO.WebBindCardPersonalReqVO;
import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.model.lijiang.SubAccountCard;
import com.ih2ome.model.lijiang.SubOrders;

import java.io.IOException;

/**
 * @author Sky
 * create 2018/08/09
 * email sky.li@ixiaoshuidi.com
 **/
public interface PinganMchService {

    /**
     * 开通商户子账户
     *
     * @param userId
     * @return
     * @throws PinganMchException
     */
    PinganMchRegisterResVO registerAccount(Integer userId) throws PinganMchException, IOException;


    /**
     * 绑定银行卡发送短信验证码(鉴权)
     *
     * @param subAccount
     * @param bankType
     * @param codeReqVO  @throws PinganMchException
     * @throws IOException
     */
    void bindCardSendMessage(SubAccount subAccount, String bankType, WebBindCardPersonalReqVO codeReqVO) throws PinganMchException, IOException;

    /**
     * 绑定个人银行卡(短信校验)
     *
     * @param subAccount
     * @param reqVO
     */
    void bindPersonalCardVertify(SubAccount subAccount, WebBindCardPersonalReqVO reqVO) throws PinganMchException, IOException;

    /**
     * 绑定银行卡发送金额验证码(鉴权)
     *
     * @param subAccount
     * @param bankType
     * @param companyReqVO
     */
    String bindCardSendAmount(SubAccount subAccount, String bankType, WebBindCardCompanyReqVO companyReqVO) throws IOException, PinganMchException;

    /**
     * 查询会员绑定信息(测试)
     *
     * @throws PinganMchException
     */
    void queryMemberBindInfo() throws PinganMchException, IOException;

    /**
     * 查询小额鉴权转账结果
     *
     * @throws PinganMchException
     * @throws IOException
     */
    void queryTransferinfo(String tranSeqNo, String tranDate) throws PinganMchException, IOException;

    /**
     * 绑定企业银行卡(金额校验)
     *
     * @param subAccount
     * @param reqVO
     */
    void bindCompanyCardVertify(SubAccount subAccount, WebBindCardCompanyReqVO reqVO) throws PinganMchException, IOException;


    /**
     * 查询子账户可用余额
     *
     * @param subAccount
     * @return
     * @throws PinganMchException
     * @throws IOException
     */
    PinganMchQueryBalanceResVO queryBalance(SubAccount subAccount) throws PinganMchException, IOException;

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
    String withDrawCash(SubAccount subAccount, SubAccountCard subAccountCard, Double withdrawMoney, Double withdrawCharge) throws PinganMchException, IOException;


    /**
     * 查询转账交易状态
     *
     * @param tranSeqNo
     * @return
     * @throws PinganMchException
     * @throws IOException
     */
    PinganMchQueryTranStatusResVO queryTranStatus(String tranSeqNo) throws PinganMchException, IOException;

    /**
     * 子账户解绑银行卡
     *
     * @param subAccount
     * @param subAccountCard
     * @throws PinganMchException
     * @throws IOException
     */
    void unbindBankCard(SubAccount subAccount, SubAccountCard subAccountCard) throws PinganMchException, IOException;


    /**
     * 查询对账文件信息
     *
     * @param fileType
     * @param fileDate
     * @return
     * @throws PinganMchException
     * @throws IOException
     */
    PinganMchQueryReconciliationDocResVO queryReconciliationFile(String fileType, String fileDate) throws PinganMchException, IOException;

    /**
     * 查询充值明细交易的状态，用于确定子订单是否已入账到对应子账户。
     *
     * @param orderNo
     * @return
     * @throws PinganMchException
     * @throws IOException
     */
    PinganMchChargeDetailResVO queryChargeDetail(String orderNo) throws PinganMchException, IOException;

    /**
     * 平台调账
     *
     * @return
     * @throws PinganMchException
     * @throws IOException
     */
    PinganMchAccRegulationResVO accountRegulation(PinganMchAccRegulationReqVO reqVO) throws PinganMchException, IOException;

    /**
     * 平台补帐
     *
     * @param orderNo 总订单的交易流水号
     * @param amt
     * @return
     * @throws PinganMchException
     * @throws IOException
     */
    PinganMchAccSupplyResVO accountSupply(String orderNo, String amt) throws PinganMchException, IOException;

    /**
     * 修改会员绑定提现账户的大小额行号，超网行号和银行名称
     *
     * @param subAcctNo  子账户账号
     * @param bankNo     会员绑定账号（银行卡号）
     * @param branchName 开户行名称
     * @param branchId   大小额行号
     * @param supId      超级网银行号
     * @throws PinganMchException
     * @throws IOException
     */
    void maintainAccountBank(String subAcctNo, String bankNo, String branchName, String branchId, String supId) throws PinganMchException, IOException;


    /**
     * 根据会员代码查询会员子账号
     *
     * @param memberId
     * @return
     * @throws PinganMchException
     * @throws IOException
     */
    String queryCustAcctId(String memberId) throws PinganMchException, IOException;

    /**
     * 查询银行时间段内清分提现明细
     *
     * @param subAcctNo    子账号
     * @param functionFlag 1:当日，2：历史
     * @param queryFlag    2：提现 3：清分
     * @param beginDate    开始日期
     * @param endDate      结束日期
     * @param pageNum      页码
     * @return
     * @throws PinganMchException
     * @throws IOException
     */
    PinganMchQueryCashDetailResVO queryCashDetail(String subAcctNo, String functionFlag, String queryFlag, String beginDate, String endDate, String pageNum) throws PinganMchException, IOException;
}
