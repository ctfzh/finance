package com.ih2ome.service;

import com.ih2ome.common.Exception.PinganMchException;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchRegisterReqVO;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchRegisterResVO;

import java.io.IOException;

/**
 * @author Sky
 * create 2018/08/09
 * email sky.li@ixiaoshuidi.com
 **/
public interface PinganMchService {

    /**
     * 开通商户子账户
     *
     * @param userId
     * @return
     * @throws PinganMchException
     */
    PinganMchRegisterResVO registerAccount(Integer userId) throws PinganMchException, IOException;

}
