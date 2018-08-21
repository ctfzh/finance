package com.ih2ome.service;

import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.model.lijiang.SubAccountCard;

/**
 * @author Sky
 * create 2018/08/21
 * email sky.li@ixiaoshuidi.com
 **/
public interface SubWithdrawRecordService {
    /**
     * 新增提现记录
     *
     * @param userId
     * @param subAccount
     * @param subAccountCard
     * @param withdrawMoney
     * @param withdrawCharge
     * @param serialNo
     */
    void insertWithdrawRecord(Integer userId, SubAccount subAccount, SubAccountCard subAccountCard, Double withdrawMoney, Double withdrawCharge, String serialNo);

}
