package com.ih2ome.service;

import com.ih2ome.common.Exception.PinganApiException;
import com.ih2ome.common.PageVO.*;

import java.util.List;

/**
 * @author Sky
 * create 2018/07/26
 * email sky.li@ixiaoshuidi.com
 **/
public interface PinganApiService {
    /**
     * 获取门店支付方式列表
     *
     * @param pinganWxPayListRequestVO
     * @return
     * @throws PinganApiException
     */
    public List<PinganWxPayListResponseVO> paylist(PinganWxPayListRequestVO pinganWxPayListRequestVO) throws PinganApiException;


    /**
     * 获取门店订单列表
     *
     * @param pinganWxOrderRequestVO
     * @return
     * @throws PinganApiException
     */
    public PinganWxOrderResponseVO queryOrderList(PinganWxOrderRequestVO pinganWxOrderRequestVO) throws PinganApiException;

}
