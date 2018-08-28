package com.ih2ome.service.impl;

import com.ih2ome.common.Exception.WebPaymentsException;
import com.ih2ome.common.PageVO.WebVO.WebRegisterResVO;
import com.ih2ome.dao.lijiang.SubAccountDao;
import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.service.WebPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public WebRegisterResVO registerAccount(Integer userId, String subAccountNo) throws WebPaymentsException {
        SubAccount subAccount = new SubAccount();
        subAccount.setCreatedAt(new Date());
        subAccount.setCreatedById(userId);
        subAccount.setUserId(userId);
        subAccount.setIsDelete(0);
        subAccount.setVersion(0);
        subAccount.setAccount(subAccountNo);
        subAccountDao.insert(subAccount);
        WebRegisterResVO webRegisterResVO = new WebRegisterResVO();
        webRegisterResVO.setUserId(userId);
        webRegisterResVO.setSubAccountNo(subAccountNo);
        return webRegisterResVO;
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

    /**
     * 处理提现金额和手续费
     *
     * @param money
     * @param cashMoney
     * @param charge
     * @return
     */
    @Override
    public Map<String, Double> disposeMoneyAndCharge(Double money, Double cashMoney, Double charge) {
        Map<String, Double> moneyAndCharge = new HashMap<String, Double>();
        Double withdrawMoney = 0.0;
        Double withdrawCharge = 0.0;
        if (money <= cashMoney) {
            withdrawMoney = money;
            double balance = cashMoney - money;
            if (balance <= charge) {
                withdrawCharge = balance;
            } else {
                withdrawCharge = charge;
            }
        } else {
            withdrawMoney = cashMoney;
            withdrawCharge = 0.0;
        }
        moneyAndCharge.put("money", withdrawMoney);
        moneyAndCharge.put("charge", withdrawCharge);
        return moneyAndCharge;
    }
}
