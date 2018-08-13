package com.ih2ome.service.impl;

import com.ih2ome.dao.lijiang.ZjjzCnapsBanktypeDao;
import com.ih2ome.model.lijiang.ZjjzCnapsBanktype;
import com.ih2ome.service.ZjjzCnapsBanktypeService;
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
public class ZjjzCnapsBanktypeServiceImpl implements ZjjzCnapsBanktypeService {

    @Autowired
    private ZjjzCnapsBanktypeDao zjjzCnapsBanktypeDao;

    /**
     * 查询所有的银行类别
     *
     * @return
     */
    @Override
    public List<ZjjzCnapsBanktype> getBankType() {
        List<ZjjzCnapsBanktype> banktypes = zjjzCnapsBanktypeDao.selectAll();
        return banktypes;
    }
}
