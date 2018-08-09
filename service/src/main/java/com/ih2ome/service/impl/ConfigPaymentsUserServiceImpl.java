package com.ih2ome.service.impl;

import com.ih2ome.dao.caspain.ConfigPaymentsUserDao;
import com.ih2ome.model.caspain.ConfigPaymentsUser;
import com.ih2ome.service.ConfigPaymentsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sky
 * create 2018/08/07
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class ConfigPaymentsUserServiceImpl implements ConfigPaymentsUserService {

    @Autowired
    private ConfigPaymentsUserDao configPaymentsUserDao;

    /**
     * 判断客户类型(true:使用分账用户, false:未使用分账用户)
     *
     * @param userId
     * @return
     */
    @Override
    public Boolean judgeUserType(Integer userId) {
        Example example = new Example(ConfigPaymentsUser.class);
        example.createCriteria().andEqualTo("createdById", userId);
        ConfigPaymentsUser configPaymentsUser = configPaymentsUserDao.selectOneByExample(example);
        if (configPaymentsUser != null) {
            Integer userType = configPaymentsUser.getUserType();
            if (userType == 1 || userType == 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据用户id查询用户支付方式展示，使用渠道展示
     *
     * @param userId
     * @return
     */
    @Override
    public Map<String, Integer> selectUserType(Integer userId) {
        Example example = new Example(ConfigPaymentsUser.class);
        example.createCriteria().andEqualTo("createdById", userId);
        ConfigPaymentsUser configPaymentsUser = configPaymentsUserDao.selectOneByExample(example);
        Map<String, Integer> map = new HashMap<>();
        if (configPaymentsUser != null) {
            map.put("userType", configPaymentsUser.getUserType());
            map.put("wxShow", configPaymentsUser.getWxShow());
            map.put("aliShow", configPaymentsUser.getAliShow());
            map.put("cardShow", configPaymentsUser.getCardShow());
        } else {
            map.put("userType", 0);
            map.put("wxShow", 1);
            map.put("aliShow", 1);
            map.put("cardShow", 1);
        }
        return map;
    }
}
