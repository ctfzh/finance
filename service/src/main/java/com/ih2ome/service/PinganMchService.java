package com.ih2ome.service;

import com.ih2ome.common.Exception.PinganMchException;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchRegisterResVO;
import com.ih2ome.common.PageVO.WebVO.WebBindCardPersonalReqVO;
import com.ih2ome.model.lijiang.SubAccount;

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
     * 查询会员绑定信息
     *
     * @throws PinganMchException
     */
    void queryMemberBindInfo() throws PinganMchException, IOException;
}
