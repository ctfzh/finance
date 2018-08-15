package com.ih2ome.dao.lijiang;

import com.ih2ome.common.PageVO.WebVO.WebSearchCnapsVO;
import com.ih2ome.dao.BaseDao;
import com.ih2ome.model.lijiang.ZjjzCnapsBankinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sky
 * create 2018/08/13
 * email sky.li@ixiaoshuidi.com
 **/
@Repository
public interface ZjjzCnapsBankinfoDao extends BaseDao<ZjjzCnapsBankinfo> {

    /**
     * 根据银行 bankclscode, citycode, bankname 查询大小额信息。
     *
     * @param bankCode
     * @param cityCode
     * @param bankName
     * @return
     */
    List<WebSearchCnapsVO> selectCnapsVOByExample(@Param("bankCode") String bankCode, @Param("cityCode") String cityCode, @Param("bankName") String bankName);
}
