package com.ih2ome.service.impl;

import com.ih2ome.dao.caspain.EmployerDao;
import com.ih2ome.dao.caspain.UserProfileDao;
import com.ih2ome.model.caspain.Employer;
import com.ih2ome.model.caspain.UserProfile;
import com.ih2ome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Sky
 * create 2018/08/20
 * email sky.li@ixiaoshuidi.com
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserProfileDao userProfileDao;
    @Autowired
    private EmployerDao employerDao;


    /**
     * 根据登录账号查询主账号Id
     *
     * @param userId
     * @return
     */
    @Override
    public Integer findLandlordId(Integer userId) {
        Example employerExample = new Example(Employer.class);
        employerExample.createCriteria().andEqualTo("userId", userId).andEqualTo("isDelete", 0);
        Employer employer = employerDao.selectOneByExample(employerExample);
        if (employer != null) {
            Integer bossId = employer.getBossId();
            Integer id = userProfileDao.selectUserIdByPrimaryKey(bossId);
            return id;
        }
        return userId;
    }
}
