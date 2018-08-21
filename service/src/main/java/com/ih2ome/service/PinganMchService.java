package com.ih2ome.service;

import com.ih2ome.common.Exception.PinganMchException;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchQueryBalanceResVO;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchQueryTranStatusReqVO;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchQueryTranStatusResVO;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchRegisterResVO;
import com.ih2ome.common.PageVO.WebVO.WebBindCardCompanyReqVO;
import com.ih2ome.common.PageVO.WebVO.WebBindCardPersonalReqVO;
import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.model.lijiang.SubAccountCard;

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
    void bindCardSendAmount(SubAccount subAccount, String bankType, WebBindCardCompanyReqVO companyReqVO) throws IOException, PinganMchException;

    /**
     * 查询会员绑定信息(测试)
     *
     * @throws PinganMchException
     */
    void queryMemberBindInfo() throws PinganMchException, IOException;

    /**
     * 查询小额鉴权转账结果（测试）
     *
     * @throws PinganMchException
     * @throws IOException
     */
    void queryTransferinfo() throws PinganMchException, IOException;

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
}
