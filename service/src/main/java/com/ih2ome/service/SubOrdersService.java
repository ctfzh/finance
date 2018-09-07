package com.ih2ome.service;

import com.ih2ome.model.lijiang.SubOrders;

/**
 * @author Sky
 * create 2018/08/27
 * email sky.li@ixiaoshuidi.com
 **/
public interface SubOrdersService {
    /**
     * 根据总订单号查询子订单记录
     *
     * @param ordersUuid
     * @return
     */
    SubOrders findSubOrdersByOrderId(String ordersUuid);
}
