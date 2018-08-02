package com.ih2ome.service;

import com.ih2ome.common.Exception.PinganApiException;
import com.ih2ome.common.Exception.SaasWxPayException;
import com.ih2ome.common.PageVO.SaasWxNotifyReqVO;
import com.ih2ome.common.PageVO.SaasWxPayOrderReqVO;
import com.ih2ome.common.PageVO.SaasWxPayOrderResVO;

/**
 * @author Sky
 * create 2018/08/01
 * email sky.li@ixiaoshuidi.com
 **/
public interface SaasWxPayService {


    /**
     * saas微信端下单
     *
     * @param reqVO
     * @return
     * @throws SaasWxPayException
     */
    SaasWxPayOrderResVO placeOrder(SaasWxPayOrderReqVO reqVO) throws SaasWxPayException, PinganApiException;

    /**
     * 支付成功回调
     *
     * @param saasWxNotifyReqVO
     * @return
     */
    Boolean notify(SaasWxNotifyReqVO saasWxNotifyReqVO);
}
