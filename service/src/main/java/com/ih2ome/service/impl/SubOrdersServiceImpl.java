package com.ih2ome.service.impl;

import com.ih2ome.dao.lijiang.SubOrdersDao;
import com.ih2ome.model.lijiang.SubOrders;
import com.ih2ome.service.SubOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Sky
 * create 2018/08/27
 * email sky.li@ixiaoshuidi.com
 **/
@Service
@Transactional
public class SubOrdersServiceImpl implements SubOrdersService {
    @Autowired
    private SubOrdersDao subOrdersDao;


    /**
     * 根据总订单号查询子订单记录
     *
     * @param ordersUuid
     * @return
     */
    @Override
    public SubOrders findSubOrdersByOrderId(String ordersUuid) {
        Example example = new Example(SubOrders.class);
        example.createCriteria().andEqualTo("orderId", ordersUuid);
        SubOrders subOrders = subOrdersDao.selectOneByExample(example);
        return subOrders;
    }
}
