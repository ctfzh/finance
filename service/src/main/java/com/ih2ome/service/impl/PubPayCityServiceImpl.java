package com.ih2ome.service.impl;

import com.ih2ome.dao.lijiang.PubPayCityDao;
import com.ih2ome.model.lijiang.PubPayCity;
import com.ih2ome.service.PubPayCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Sky
 * create 2018/08/13
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class PubPayCityServiceImpl implements PubPayCityService {

    @Autowired
    private PubPayCityDao pubPayCityDao;

    /**
     * 根据省份获取城市列表
     *
     * @param province
     * @return
     */
    @Override
    public List<PubPayCity> getCitiesByProvince(String province) {
        Example example = new Example(PubPayCity.class);
        example.createCriteria().andEqualTo("cityNodecode", province).andEqualTo("cityAreatype", "2");
        List<PubPayCity> cities = pubPayCityDao.selectByExample(example);
        return cities;
    }

    /**
     * 根据城市获取区/县
     *
     * @param city
     * @return
     */
    @Override
    public List<PubPayCity> getDistrictsByCity(String city) {
        Example example = new Example(PubPayCity.class);
        PubPayCity pubPayCity = new PubPayCity();
        pubPayCity.setCityTopareacode2(city);
        pubPayCity.setCityAreatype("3");
        example.createCriteria().andEqualTo(pubPayCity);
        List<PubPayCity> districts = pubPayCityDao.selectByExample(example);
        return districts;
    }
}
