package com.ih2ome.service;

import com.ih2ome.model.lijiang.PubPayCity;

import java.util.List;

/**
 * @author Sky
 * create 2018/08/13
 * email sky.li@ixiaoshuidi.com
 **/
public interface PubPayCityService {
    /**
     * 根据省份获取城市列表
     *
     * @param province
     * @return
     */
    List<PubPayCity> getCitiesByProvince(String province);
}
