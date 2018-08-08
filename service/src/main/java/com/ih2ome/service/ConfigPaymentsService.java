package com.ih2ome.service;

import com.ih2ome.common.PageVO.CalculateChargeVO;
import com.ih2ome.common.PageVO.ConfigPayChannelsVO;
import com.ih2ome.common.PageVO.ConfigPaymentsVO;
import com.ih2ome.common.enums.ConfigPayWayEnum;

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
     * @param bool
     * @return
     */
    ConfigPaymentsVO findConfigPaymentsInfo(Integer userId, Boolean bool) throws Exception;

    /**
     * 设置支付配置
     *
     * @param userId
     * @param assumePerson
     * @param bool
     * @return
     */
    void setConfigPaymentsInfo(Integer userId, String assumePerson, Boolean bool) throws Exception;

    /**
     * 计算费用
     *  @param userId
     * @param payWay
     * @param money  @return
     * @param bool
     */
    CalculateChargeVO calculateCharge(Integer userId, ConfigPayWayEnum payWay, Double money, Boolean bool) throws Exception;

    /**
     * 根据用户id查询支付渠道手续费用配置
     *
     * @param userId
     * @param bool
     * @return
     */
    ConfigPayChannelsVO getConfigChannelInfo(Integer userId, Boolean bool) throws Exception;
}
