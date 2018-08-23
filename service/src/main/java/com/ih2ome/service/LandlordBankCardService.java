package com.ih2ome.service;

import com.ih2ome.common.PageVO.WebVO.WebBindCardCompanyReqVO;
import com.ih2ome.common.PageVO.WebVO.WebBindCardPersonalReqVO;
import com.ih2ome.model.lijiang.SubAccount;

/**
 * @author Sky
 * create 2018/08/20
 * email sky.li@ixiaoshuidi.com
 **/
public interface LandlordBankCardService {
    /**
     * 往landlord_bank_card插入个人绑定银行卡信息
     *
     * @param subAccount
     * @param reqVO
     */
    void insertPersonalCardInfo(SubAccount subAccount, WebBindCardPersonalReqVO reqVO);

    /**
     * 往landlord_bank_card插入企业绑定银行卡信息
     *
     * @param subAccount
     * @param reqVO
     */
    void insertCompanyCardInfo(SubAccount subAccount, WebBindCardCompanyReqVO reqVO);

    /**
     * landlord_bank_card 解绑银行卡
     *
     * @param bankNo
     */
    void unbindLandlordBankCard(String bankNo);
}
