package com.ih2ome.service.impl;

import com.ih2ome.common.enums.ConfigPayChannelEnum;
import com.ih2ome.common.enums.ConfigPayUserTypeEnum;
import com.ih2ome.dao.caspain.ConfigPaymentsUserDao;
import com.ih2ome.model.caspain.ConfigPaymentsUser;
import com.ih2ome.service.ConfigPaymentsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sky
 * create 2018/08/07
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class ConfigPaymentsUserServiceImpl implements ConfigPaymentsUserService {

    @Autowired
    private ConfigPaymentsUserDao configPaymentsUserDao;

    /**
     * 判断客户类型(true:使用分账用户, false:未使用分账用户)
     *
     * @param userId
     * @return
     */
    @Override
    public Boolean judgeUserType(Integer userId) {
        Example example = new Example(ConfigPaymentsUser.class);
        example.createCriteria().andEqualTo("createdById", userId);
        ConfigPaymentsUser configPaymentsUser = configPaymentsUserDao.selectOneByExample(example);
        if (configPaymentsUser != null) {
            Integer userType = configPaymentsUser.getUserType();
            if (userType.equals(ConfigPayUserTypeEnum.PINGAN_USER.getCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据用户id查询用户支付方式展示，使用渠道展示
     *
     * @param userId
     * @return
     */
    @Override
    public ConfigPaymentsUser selectUserType(Integer userId) {
        Example example = new Example(ConfigPaymentsUser.class);
        example.createCriteria().andEqualTo("createdById", userId).andEqualTo("isDelete", 0);
        ConfigPaymentsUser configPaymentsUser = configPaymentsUserDao.selectOneByExample(example);
        //默认原先通道全展开,使用通联微信，支付宝，连连借记卡
        if (configPaymentsUser == null) {
            ConfigPaymentsUser paymentsUser = new ConfigPaymentsUser();
            //默认普通用户
            paymentsUser.setUserType(0);
            paymentsUser.setAliShow(1);
            paymentsUser.setCardShow(1);
            paymentsUser.setWxShow(1);
            paymentsUser.setWxType(ConfigPayChannelEnum.ALLIANPAY_WX.getName());
            paymentsUser.setAliType(ConfigPayChannelEnum.ALIPAY.getName());
            paymentsUser.setCardType(ConfigPayChannelEnum.LLIANPAY_CARD.getName());
            return paymentsUser;
        }
        return configPaymentsUser;
    }
}
