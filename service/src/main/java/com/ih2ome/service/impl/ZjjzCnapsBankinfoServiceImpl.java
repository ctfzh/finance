package com.ih2ome.service.impl;

import com.ih2ome.common.PageVO.WebVO.WebSearchCnapsVO;
import com.ih2ome.dao.lijiang.ZjjzCnapsBankinfoDao;
import com.ih2ome.model.lijiang.ZjjzCnapsBankinfo;
import com.ih2ome.service.ZjjzCnapsBankinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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

    /**
     * 根据大小额行号判断是否是平安银行（1是平安银行，2.其他银行）
     *
     * @param bankCnapsNo
     * @return
     */
    @Override
    public String judgeBankTypeIsPingan(String bankCnapsNo) {
        Example example = new Example(ZjjzCnapsBankinfo.class);
        example.createCriteria().andEqualTo("bankno", bankCnapsNo).andEqualTo("status", "1");
        ZjjzCnapsBankinfo zjjzCnapsBankinfos = zjjzCnapsBankinfoDao.selectOneByExample(example);
        String bankclscode = zjjzCnapsBankinfos.getBankclscode();
        if ("307".equals(bankclscode)) {
            return "1";
        } else {
            return "2";
        }
    }
}
