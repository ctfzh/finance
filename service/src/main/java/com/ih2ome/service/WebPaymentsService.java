package com.ih2ome.service;

import com.ih2ome.common.Exception.WebPaymentsException;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchRegisterResVO;
import com.ih2ome.model.lijiang.SubAccount;

/**
 * @author Sky
 * create 2018/08/10
 * email sky.li@ixiaoshuidi.com
 **/
public interface WebPaymentsService {

    /**
     * 商户子账号注册(水滴)
     *
     * @param userId
     * @param subAccountNo
     */
    public void registerAccount(Integer userId, String subAccountNo) throws WebPaymentsException;

    /**
     * 根据用户id查询商户子账号
     *
     * @param userId
     * @return
     */
    SubAccount findAccountByUserId(Integer userId);
}
