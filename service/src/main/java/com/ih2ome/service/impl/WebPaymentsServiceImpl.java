package com.ih2ome.service.impl;

import com.ih2ome.common.Exception.WebPaymentsException;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchRegisterResVO;
import com.ih2ome.dao.lijiang.SubAccountDao;
import com.ih2ome.dao.lijiang.SubOrdersDao;
import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.service.WebPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author Sky
 * create 2018/08/10
 * email sky.li@ixiaoshuidi.com
 **/
@Service
@Transactional
public class WebPaymentsServiceImpl implements WebPaymentsService {

    @Autowired
    private SubAccountDao subAccountDao;

    /**
     * 商户注册
     *
     * @param userId
     * @param subAccountNo
     */
    @Override
    public void registerAccount(Integer userId, String subAccountNo) throws WebPaymentsException {
        SubAccount subAccount = new SubAccount();
        subAccount.setCreatedAt(new Date());
        subAccount.setCreatedById(userId);
        subAccount.setUserId(userId);
        subAccount.setIsDelete(0);
        subAccount.setVersion(0);
        subAccount.setAccount(subAccountNo);
        subAccountDao.insert(subAccount);
    }

    /**
     * 根据用户id查询商户子账号
     *
     * @param userId
     * @return
     */
    @Override
    public SubAccount findAccountByUserId(Integer userId) {
        Example example = new Example(SubAccount.class);
        example.createCriteria().andEqualTo("userId", userId).andEqualTo("isDelete", 0);
        SubAccount subAccount = subAccountDao.selectOneByExample(example);
        return subAccount;
    }
}
