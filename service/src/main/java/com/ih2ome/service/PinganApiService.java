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
     * @param pinganWxPayListReqVO
     * @return
     * @throws PinganApiException
     */
    public List<PinganWxPayListResVO> paylist(PinganWxPayListReqVO pinganWxPayListReqVO) throws PinganApiException;


    /**
     * 获取门店订单列表
     *
     * @param pinganWxOrderReqVO
     * @return
     * @throws PinganApiException
     */
    public PinganWxOrderResVO queryOrderList(PinganWxOrderReqVO pinganWxOrderReqVO) throws PinganApiException;

}
