package com.ih2ome.service;

import com.ih2ome.common.PageVO.ConfigPaymentsVO;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @author Sky
 * create 2018/07/09
 * email sky.li@ixiaoshuidi.com
 **/
public interface ConfigPaymentsService {
    /**
     * 根据用户Id获取支付配置
     *
     * @param userId
     * @return
     */
    ConfigPaymentsVO findConfigPaymentsInfo(Integer userId) throws Exception;

    /**
     * 设置支付配置
     *
     * @param userId
     * @param assumePerson
     * @return
     */
    void setConfigPaymentsInfo(Integer userId, String assumePerson) throws Exception;
}
