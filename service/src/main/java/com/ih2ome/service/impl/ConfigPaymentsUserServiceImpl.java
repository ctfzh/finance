package com.ih2ome.service.impl;

import com.ih2ome.dao.caspain.ConfigPaymentsUserDao;
import com.ih2ome.model.caspain.ConfigPaymentsUser;
import com.ih2ome.service.ConfigPaymentsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Sky
 * create 2018/08/07
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class ConfigPaymentsUserServiceImpl implements ConfigPaymentsUserService {

    @Autowired
    private ConfigPaymentsUserDao configPaymentsUserDao;

    @Override
    public Boolean judgeUserType(Integer userId) {
        Example example = new Example(ConfigPaymentsUser.class);
        example.createCriteria().andEqualTo("createdById", userId);
        ConfigPaymentsUser configPaymentsUser = configPaymentsUserDao.selectOneByExample(example);
        if (configPaymentsUser != null) {
            Integer userType = configPaymentsUser.getUserType();
            if (userType == 1) {
                return true;
            }
        }
        return false;
    }
}
