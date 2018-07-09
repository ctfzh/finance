package com.ih2ome.service;

import com.ih2ome.common.PageVO.ConfigPaymentsVO;

/**
 * @author Sky
 * create 2018/07/09
 * email sky.li@ixiaoshuidi.com
 **/
public interface ConfigPaymentsService {
    /**
     * 根据userId查询支付设置
     *
     * @param userId
     * @return
     */
    ConfigPaymentsVO findConfigPaymentsInfo(Integer userId) throws Exception;
}
