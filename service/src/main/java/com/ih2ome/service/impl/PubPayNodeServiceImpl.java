package com.ih2ome.service.impl;

import com.ih2ome.model.caspain.PubPayNodeDao;
import com.ih2ome.model.lijiang.PubPayNode;
import com.ih2ome.service.PubPayNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sky
 * create 2018/08/13
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class PubPayNodeServiceImpl implements PubPayNodeService {

    @Autowired
    private PubPayNodeDao pubPayNodeDao;
}
