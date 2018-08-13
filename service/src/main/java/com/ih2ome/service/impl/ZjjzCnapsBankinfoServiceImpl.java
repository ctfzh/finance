package com.ih2ome.service.impl;

import com.ih2ome.model.caspain.ZjjzCnapsBankinfoDao;
import com.ih2ome.service.ZjjzCnapsBankinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sky
 * create 2018/08/13
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class ZjjzCnapsBankinfoServiceImpl implements ZjjzCnapsBankinfoService {

    @Autowired
    private ZjjzCnapsBankinfoDao zjjzCnapsBankinfoDao;
}
