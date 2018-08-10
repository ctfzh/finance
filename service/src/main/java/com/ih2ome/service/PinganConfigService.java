package com.ih2ome.service;

import com.ih2ome.common.PageVO.PinganWxPayVO.PinganWxConfigAddReqVO;
import com.ih2ome.common.PageVO.PinganWxPayVO.PinganWxConfigAddResVO;

/**
 * @author Sky
 * create 2018/07/27
 * email sky.li@ixiaoshuidi.com
 * 平安银行商户支付配置
 **/
public interface PinganConfigService {

    /**
     * 商户支付配置 ---添加
     *
     * @param pinganWxConfigAddReqVO
     * @return
     */
    public PinganWxConfigAddResVO addConfig(PinganWxConfigAddReqVO pinganWxConfigAddReqVO);

    /**
     * 商户支付配置 ---查询
     *
     * @param pinganWxConfigAddReqVO
     */
    void getConfig(PinganWxConfigAddReqVO pinganWxConfigAddReqVO);
}
