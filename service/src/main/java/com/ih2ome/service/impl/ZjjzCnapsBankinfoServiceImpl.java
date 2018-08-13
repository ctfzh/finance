package com.ih2ome.service.impl;

import com.ih2ome.common.PageVO.WebSearchCnapsVO;
import com.ih2ome.dao.lijiang.ZjjzCnapsBankinfoDao;
import com.ih2ome.service.ZjjzCnapsBankinfoService;
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
public class ZjjzCnapsBankinfoServiceImpl implements ZjjzCnapsBankinfoService {

    @Autowired
    private ZjjzCnapsBankinfoDao zjjzCnapsBankinfoDao;

    /**
     * 获取大小额行号和名称
     *
     * @param bankCode
     * @param cityCode
     * @param bankName
     * @return
     */
    @Override
    public List<WebSearchCnapsVO> searchCnaps(String bankCode, String cityCode, String bankName) {
        List<WebSearchCnapsVO> cnaps = zjjzCnapsBankinfoDao.selectCnapsVOByExample(bankCode, cityCode, bankName);
        return cnaps;
    }
}
