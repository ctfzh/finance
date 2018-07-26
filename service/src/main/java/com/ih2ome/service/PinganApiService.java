package com.ih2ome.service;

import com.ih2ome.common.Exception.PinganApiException;
import com.ih2ome.common.PageVO.PinganWxPayListRequestVO;
import com.ih2ome.common.PageVO.PinganWxPayListResponseVO;
import com.ih2ome.common.PageVO.PinganWxSignVerifyVO;

import java.util.List;

/**
 * @author Sky
 * create 2018/07/26
 * email sky.li@ixiaoshuidi.com
 **/
public interface PinganApiService {
    /**
     * @param pinganWxPayListRequestVO
     * @return
     */
    public List<PinganWxPayListResponseVO> paylist(PinganWxPayListRequestVO pinganWxPayListRequestVO) throws PinganApiException;

}
