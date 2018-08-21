package com.ih2ome.service;

/**
 * @author Sky
 * create 2018/08/20
 * email sky.li@ixiaoshuidi.com
 **/
public interface UserService {
    /**
     * 根据登录账号查询主账号Id
     *
     * @param userId
     * @return
     */
    Integer findLandlordId(Integer userId);
}
