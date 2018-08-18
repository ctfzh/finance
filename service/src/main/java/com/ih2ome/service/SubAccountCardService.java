package com.ih2ome.service;

import com.ih2ome.common.PageVO.WebVO.WebBindCardCompanyReqVO;
import com.ih2ome.common.PageVO.WebVO.WebBindCardPersonalReqVO;
import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.model.lijiang.SubAccountCard;

/**
 * @author Sky
 * create 2018/08/16
 * email sky.li@ixiaoshuidi.com
 **/
public interface SubAccountCardService {
    /**
     * 绑定个人银行卡到账号
     *
     * @param subAccount
     * @param reqVO
     */
    void insertPersonalCardInfo(SubAccount subAccount, WebBindCardPersonalReqVO reqVO);

    /**
     * 绑定企业银行卡到账号
     *
     * @param subAccount
     * @param reqVO
     */
    void insertCompanyCardInfo(SubAccount subAccount, WebBindCardCompanyReqVO reqVO);

    /**
     * 根据会员子账号查询绑定银行卡信息
     *
     * @param accountId
     * @return
     */
    SubAccountCard findSubAccountByAccountId(Integer accountId);
}
