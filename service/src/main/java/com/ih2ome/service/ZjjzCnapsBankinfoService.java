package com.ih2ome.service;

import com.ih2ome.common.PageVO.WebVO.WebSearchCnapsVO;

import java.util.List;

/**
 * @author Sky
 * create 2018/08/13
 * email sky.li@ixiaoshuidi.com
 **/
public interface ZjjzCnapsBankinfoService {
    /**
     * 获取大小额行号和名称
     *
     * @param bankCode
     * @param cityCode
     * @param bankName
     * @return
     */
    List<WebSearchCnapsVO> searchCnaps(String bankCode, String cityCode, String bankName);
}
