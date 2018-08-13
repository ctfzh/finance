package com.ih2ome.service.impl;

import com.ih2ome.dao.lijiang.PubPayCityDao;
import com.ih2ome.service.PubPayCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sky
 * create 2018/08/13
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class PubPayCityServiceImpl implements PubPayCityService {

    @Autowired
    private PubPayCityDao pubPayCityDao;
}
