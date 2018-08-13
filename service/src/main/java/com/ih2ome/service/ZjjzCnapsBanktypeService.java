package com.ih2ome.service;

import com.ih2ome.model.lijiang.ZjjzCnapsBanktype;

import java.util.List;

/**
 * @author Sky
 * create 2018/08/13
 * email sky.li@ixiaoshuidi.com
 **/
public interface ZjjzCnapsBanktypeService {
    /**
     * 查询所有的银行类别
     *
     * @return
     */
    List<ZjjzCnapsBanktype> getBankType();
}
