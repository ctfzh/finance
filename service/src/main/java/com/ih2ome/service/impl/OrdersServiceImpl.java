package com.ih2ome.service.impl;

import com.ih2ome.dao.lijiang.OrdersDao;
import com.ih2ome.model.lijiang.Orders;
import com.ih2ome.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Sky
 * create 2018/09/06
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    /**
     * 根据总订单的交易号(订单号) 查询总订单记录
     *
     * @param out_no
     * @return
     */
    @Override
    public Orders findOrdersByOrderId(String out_no) {
        Example example = new Example(Orders.class);
        example.createCriteria().andEqualTo("orderId", out_no).andEqualTo("paid", 1);
        Orders orders = ordersDao.selectOneByExample(example);
        return orders;
    }
}

