package com.ih2ome.service;

import com.ih2ome.model.lijiang.Orders;

/**
 * @author Sky
 * create 2018/09/06
 * email sky.li@ixiaoshuidi.com
 **/
public interface OrdersService {
    /**
     * 根据总订单的交易号(订单号) 查询总订单记录
     *
     * @param out_no
     * @return
     */
    Orders findOrdersByOrderId(String out_no);
}
