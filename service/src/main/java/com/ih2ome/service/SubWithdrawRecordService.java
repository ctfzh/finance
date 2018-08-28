package com.ih2ome.service;

import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.model.lijiang.SubAccountCard;
import com.ih2ome.model.lijiang.SubWithdrawRecord;

import java.util.List;

/**
 * @author Sky
 * create 2018/08/21
 * email sky.li@ixiaoshuidi.com
 **/
public interface SubWithdrawRecordService {
    /**
     * 新增提现记录
     *  @param userId
     * @param subAccount
     * @param subAccountCard
     * @param withdrawMoney
     * @param withdrawCharge
     * @param serialNo
     * @param serialNo
     */
    void insertWithdrawRecord(Integer userId, SubAccount subAccount, SubAccountCard subAccountCard, Double withdrawMoney, Double withdrawCharge, String serialNo, String tradeId);

    /**
     * 根据主账号id查询该账号下所有的提现状态为提现中
     *
     * @param landlordId
     * @return
     */
    List<SubWithdrawRecord> queryWithdrawRecords(Integer landlordId);

    /**
     * 修改订单状态
     *
     * @param withdrawRecord
     */
    void updateWithdrawStatus(SubWithdrawRecord withdrawRecord);
}
