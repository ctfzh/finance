package com.ih2ome.service;

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
}
