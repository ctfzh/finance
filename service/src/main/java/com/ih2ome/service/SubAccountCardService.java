package com.ih2ome.service;

import com.ih2ome.common.PageVO.WebVO.WebBindCardPersonalReqVO;
import com.ih2ome.model.lijiang.SubAccount;

/**
 * @author Sky
 * create 2018/08/16
 * email sky.li@ixiaoshuidi.com
 **/
public interface SubAccountCardService {
    /**
     * 绑定银行卡到账号
     *
     * @param subAccount
     * @param reqVO
     */
    void insertCardInfo(SubAccount subAccount, WebBindCardPersonalReqVO reqVO);
}
