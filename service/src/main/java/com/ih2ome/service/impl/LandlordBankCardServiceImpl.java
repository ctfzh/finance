package com.ih2ome.service.impl;

import com.ih2ome.common.PageVO.WebVO.WebBindCardCompanyReqVO;
import com.ih2ome.common.PageVO.WebVO.WebBindCardPersonalReqVO;
import com.ih2ome.dao.caspain.LandlordBankCardDao;
import com.ih2ome.model.caspain.LandlordBankCard;
import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.service.LandlordBankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Sky
 * create 2018/08/20
 * email sky.li@ixiaoshuidi.com
 **/
@Service
@Transactional
public class LandlordBankCardServiceImpl implements LandlordBankCardService {

    @Autowired
    private LandlordBankCardDao landlordBankCardDao;

    /**
     * 往landlord_bank_card插入绑定个人银行卡信息
     *
     * @param subAccount
     * @param personalReqVO
     */
    @Override
    public void insertPersonalCardInfo(SubAccount subAccount, WebBindCardPersonalReqVO personalReqVO) {
        LandlordBankCard bankCard = new LandlordBankCard();
        bankCard.setBankName(personalReqVO.getBankName());
        bankCard.setBranchBank(personalReqVO.getBankBranchName());
        bankCard.setUserId(subAccount.getUserId());
        bankCard.setCreatedAt(new Date());
        bankCard.setCreatedById(subAccount.getUserId());
        bankCard.setCardNo(personalReqVO.getBankCardNo());
        bankCard.setOwnerName(personalReqVO.getUserName());
        bankCard.setIsDelete(0);
        bankCard.setVersion(0);
        bankCard.setUsedType(1);
        landlordBankCardDao.insert(bankCard);
    }


    /**
     * 往landlord_bank_card插入绑定企业银行卡信息
     *
     * @param subAccount
     * @param companyReqVO
     */
    @Override
    public void insertCompanyCardInfo(SubAccount subAccount, WebBindCardCompanyReqVO companyReqVO) {
        LandlordBankCard bankCard = new LandlordBankCard();
        bankCard.setBankName(companyReqVO.getBankName());
        bankCard.setBranchBank(companyReqVO.getBankBranchName());
        bankCard.setUserId(subAccount.getUserId());
        bankCard.setCreatedAt(new Date());
        bankCard.setCreatedById(subAccount.getUserId());
        bankCard.setCardNo(companyReqVO.getBankCardNo());
        bankCard.setOwnerName(companyReqVO.getUserName());
        bankCard.setIsDelete(0);
        bankCard.setVersion(0);
        bankCard.setUsedType(1);
        landlordBankCardDao.insert(bankCard);
    }
}
