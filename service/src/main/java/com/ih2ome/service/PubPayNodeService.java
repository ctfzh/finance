package com.ih2ome.service;

import com.ih2ome.model.lijiang.PubPayNode;

import java.util.List;

/**
 * @author Sky
 * create 2018/08/13
 * email sky.li@ixiaoshuidi.com
 **/
public interface PubPayNodeService {
    /**
     * 获取省份
     *
     * @return
     */
    List<PubPayNode> getProvinces();
}
