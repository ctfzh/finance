package com.ih2ome.service.impl;

import com.ih2ome.dao.lijiang.PubPayNodeDao;
import com.ih2ome.model.lijiang.PubPayNode;
import com.ih2ome.service.PubPayNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sky
 * create 2018/08/13
 * email sky.li@ixiaoshuidi.com
 **/
@Service
@Transactional
public class PubPayNodeServiceImpl implements PubPayNodeService {

    @Autowired
    private PubPayNodeDao pubPayNodeDao;

    /**
     * 获取省份
     *
     * @return
     */
    @Override
    public List<PubPayNode> getProvinces() {
        List<PubPayNode> pubPayNodes = pubPayNodeDao.selectAll();
        return pubPayNodes;
    }
}
