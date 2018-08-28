package com.ih2ome.service;

import com.ih2ome.common.Exception.WebPaymentsException;
import com.ih2ome.common.PageVO.WebVO.WebRegisterResVO;
import com.ih2ome.model.lijiang.SubAccount;

import java.util.Map;

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
    WebRegisterResVO registerAccount(Integer userId, String subAccountNo) throws WebPaymentsException;

    /**
     * 根据用户id查询商户子账号
     *
     * @param userId
     * @return
     */
    SubAccount findAccountByUserId(Integer userId);

    /**
     * 处理提现金额和手续费
     *
     * @param money
     * @param cashMoney
     * @param charge
     * @return
     */
    Map<String, Double> disposeMoneyAndCharge(Double money, Double cashMoney, Double charge);
}
