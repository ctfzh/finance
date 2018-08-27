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
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

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

    /**
     * landlord_bank_card 解绑银行卡
     *
     * @param bankNo
     */
    @Override
    public void unbindLandlordBankCard(String bankNo) {
        Example example = new Example(LandlordBankCard.class);
        example.createCriteria().andEqualTo("isDelete", 0).andEqualTo("cardNo", bankNo)
                .andEqualTo("usedType", 1);
        List<LandlordBankCard> landlordBankCards = landlordBankCardDao.selectByExample(example);
        for (LandlordBankCard landlordBankCard : landlordBankCards) {
            landlordBankCard.setIsDelete(1);
            landlordBankCard.setDeletedAt(new Date());
            landlordBankCard.setDeletedById(landlordBankCard.getCreatedById());
            landlordBankCardDao.updateByPrimaryKeySelective(landlordBankCard);
        }
    }

    /**
     * 根据主账号id查询绑定银行卡信息
     *
     * @param landlordId
     * @param userType
     * @return
     */
    @Override
    public LandlordBankCard findBankCardInfo(Integer landlordId, Integer userType) {
        Example example = new Example(LandlordBankCard.class);
        example.createCriteria().andEqualTo("userId", landlordId).andEqualTo("isDelete", 0)
                .andEqualTo("usedType", userType);
        LandlordBankCard landlordBankCard = landlordBankCardDao.selectOneByExample(example);
        return landlordBankCard;
    }
}
