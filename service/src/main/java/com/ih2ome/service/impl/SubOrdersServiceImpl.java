package com.ih2ome.service.impl;

import com.ih2ome.dao.lijiang.SubOrdersDao;
import com.ih2ome.service.SubOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
