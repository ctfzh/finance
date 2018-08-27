package com.ih2ome.service;

import com.ih2ome.model.caspain.ConfigPaymentsUser;

import java.util.Map;

/**
 * @author Sky
 * create 2018/08/07
 * email sky.li@ixiaoshuidi.com
 **/
public interface ConfigPaymentsUserService {

    /**
     * 判断客户类型(true:使用分账用户, false:未使用分账用户)
     *
     * @param userId
     * @return
     */
    Boolean judgeUserType(Integer userId);

    /**
     * 根据用户id查询用户支付方式展示，使用渠道展示
     *
     * @param userId
     * @return
     */
    ConfigPaymentsUser selectUserType(Integer userId);
}
