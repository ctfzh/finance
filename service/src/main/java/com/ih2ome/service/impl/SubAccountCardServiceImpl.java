package com.ih2ome.service.impl;

import com.ih2ome.common.PageVO.WebVO.WebBindCardCompanyReqVO;
import com.ih2ome.common.PageVO.WebVO.WebBindCardPersonalReqVO;
import com.ih2ome.dao.lijiang.SubAccountCardDao;
import com.ih2ome.dao.lijiang.SubAccountDao;
import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.model.lijiang.SubAccountCard;
import com.ih2ome.service.SubAccountCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author Sky
 * create 2018/08/16
 * email sky.li@ixiaoshuidi.com
 **/
@Service
@Transactional
public class SubAccountCardServiceImpl implements SubAccountCardService {

    @Autowired
    private SubAccountCardDao subAccountCardDao;

    /**
     * 绑定银行卡到账号
     *
     * @param subAccount
     * @param reqVO
     */
    @Override
    public void insertPersonalCardInfo(SubAccount subAccount, WebBindCardPersonalReqVO reqVO) {
        SubAccountCard subAccountCard = new SubAccountCard();
        subAccountCard.setCreatedAt(new Date());
        subAccountCard.setCreatedById(reqVO.getUserId());
        subAccountCard.setBankName(reqVO.getBankName());
        subAccountCard.setBankNo(reqVO.getBankCardNo());
        subAccountCard.setBindMobile(reqVO.getMobile());
        subAccountCard.setCnapsNo(reqVO.getBankCnapsNo());
        subAccountCard.setIdCardNo(reqVO.getIdCardNo());
        subAccountCard.setIdCardName(reqVO.getUserName());
        //personal:个人绑卡  company:企业绑卡
        subAccountCard.setBindType("personal");
        //绑定状态 0未绑定，1绑定
        subAccountCard.setIsBind(1);
        subAccountCard.setVersion(0);
        subAccountCard.setIsDelete(0);
        subAccountCard.setSubAccountId(subAccount.getId());
        subAccountCardDao.insert(subAccountCard);
    }

    /**
     * 绑定企业银行卡到账号
     *
     * @param subAccount
     * @param reqVO
     */
    @Override
    public void insertCompanyCardInfo(SubAccount subAccount, WebBindCardCompanyReqVO reqVO) {
        SubAccountCard subAccountCard = new SubAccountCard();
        subAccountCard.setCreatedAt(new Date());
        subAccountCard.setCreatedById(reqVO.getUserId());
        subAccountCard.setBankName(reqVO.getBankName());
        subAccountCard.setBankNo(reqVO.getBankCardNo());
        subAccountCard.setBindMobile(reqVO.getMobile());
        subAccountCard.setCnapsNo(reqVO.getBankCnapsNo());
        subAccountCard.setIdCardNo(reqVO.getIdCardNo());
        subAccountCard.setIdCardName(reqVO.getUserName());
        //personal:个人绑卡  company:企业绑卡
        subAccountCard.setBindType("company");
        //绑定状态 0未绑定，1绑定
        subAccountCard.setIsBind(1);
        subAccountCard.setVersion(0);
        subAccountCard.setIsDelete(0);
        subAccountCard.setSubAccountId(subAccount.getId());
        subAccountCardDao.insert(subAccountCard);
    }


    /**
     * 根据会员子账号查询绑定银行卡信息
     *
     * @param accountId
     * @return
     */
    @Override
    public SubAccountCard findSubAccountByAccountId(Integer accountId) {
        Example example = new Example(SubAccountCard.class);
        example.createCriteria().andEqualTo("isDelete", 0).andEqualTo("isBind", 1)
                .andEqualTo("subAccountId", accountId);
        SubAccountCard subAccountCard = subAccountCardDao.selectOneByExample(example);
        return subAccountCard;
    }
}
