package com.ih2ome.service.impl;

import com.ih2ome.dao.lijiang.SubWithdrawRecordDao;
import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.model.lijiang.SubAccountCard;
import com.ih2ome.model.lijiang.SubWithdrawRecord;
import com.ih2ome.service.SubWithdrawRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Sky
 * create 2018/08/21
 * email sky.li@ixiaoshuidi.com
 **/
@Service
@Transactional
public class SubWithdrawRecordServiceImpl implements SubWithdrawRecordService {

    @Autowired
    private SubWithdrawRecordDao withdrawRecordDao;

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
    @Override
    public void insertWithdrawRecord(Integer userId, SubAccount subAccount, SubAccountCard subAccountCard, Double withdrawMoney, Double withdrawCharge, String serialNo) {
        SubWithdrawRecord withdrawRecord = new SubWithdrawRecord();
        withdrawRecord.setCreatedAt(new Date());
        //提现人的用户id
        withdrawRecord.setCreatedById(userId);
        withdrawRecord.setCurrency("RMB");
        withdrawRecord.setIsDelete(0);
        withdrawRecord.setVersion(0);
        withdrawRecord.setLandlordId(subAccount.getUserId());
        withdrawRecord.setSerialNo(serialNo);
        withdrawRecord.setSubAccountCardId(subAccountCard.getId());
        withdrawRecord.setSubAccountId(subAccount.getId());
        withdrawRecord.setWithdrawCharge(withdrawCharge);
        withdrawRecord.setWithdrawMoney(withdrawMoney);
        //提现状态：0提现中，1提现成功 2提现失败
        withdrawRecord.setWithdrawStatus(0);
        withdrawRecordDao.insert(withdrawRecord);
    }
}
