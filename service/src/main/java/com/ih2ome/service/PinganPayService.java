package com.ih2ome.service;

import com.ih2ome.common.Exception.PinganWxPayException;
import com.ih2ome.common.PageVO.PinganWxPayVO.*;

import java.util.List;

/**
 * @author Sky
 * create 2018/07/26
 * email sky.li@ixiaoshuidi.com
 **/
public interface PinganPayService {
    /**
     * 获取门店支付方式列表
     *
     * @param pinganWxPayListReqVO
     * @return
     * @throws PinganWxPayException
     */
    public List<PinganWxPayListResVO> paylist(PinganWxPayListReqVO pinganWxPayListReqVO) throws PinganWxPayException;


    /**
     * 获取门店订单列表
     *
     * @param pinganWxOrderReqVO
     * @return
     * @throws PinganWxPayException
     */
    public PinganWxOrderResVO queryOrderList(PinganWxOrderReqVO pinganWxOrderReqVO) throws PinganWxPayException;

    /**
     * 查询订单明细
     *
     * @param pinganWxOrderViewReqVO
     * @return
     * @throws PinganWxPayException
     */
    public PinganWxOrderViewResVO queryOrderView(PinganWxOrderViewReqVO pinganWxOrderViewReqVO) throws PinganWxPayException;

    /**
     * 下订单
     *
     * @param pinganWxPayOrderReqVO
     * @return
     * @throws PinganWxPayException
     */
    public PinganWxPayOrderResVO payOrder(PinganWxPayOrderReqVO pinganWxPayOrderReqVO) throws PinganWxPayException;
}
