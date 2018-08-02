package com.ih2ome.service.impl;

import com.ih2ome.common.Exception.PinganApiException;
import com.ih2ome.common.Exception.SaasWxPayException;
import com.ih2ome.common.PageVO.PinganWxPayOrderReqVO;
import com.ih2ome.common.PageVO.PinganWxPayOrderResVO;
import com.ih2ome.common.PageVO.SaasWxPayOrderReqVO;
import com.ih2ome.common.PageVO.SaasWxPayOrderResVO;
import com.ih2ome.common.enums.FeeTypeEnum;
import com.ih2ome.common.utils.pingan.SerialNumUtil;
import com.ih2ome.dao.lijiang.PayOrdersDao;
import com.ih2ome.model.lijiang.PayOrders;
import com.ih2ome.service.PinganApiService;
import com.ih2ome.service.SaasWxPayService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author Sky
 * create 2018/08/01
 * email sky.li@ixiaoshuidi.com
 **/
@Service
@Transactional
public class SaasWxPayServiceImpl implements SaasWxPayService {

    //文件传输用户短号
    @Value("${pingan.wxPay.uid}")
    private String uid;
    //项目地址
    @Value("${pingan.wxPay.shuidiUrl}")
    private String shuidiUrl;
    @Autowired
    private PayOrdersDao payOrdersDao;
    //平安第三方下单
    @Autowired
    private PinganApiService pinganApiService;

    /**
     * 下单
     *
     * @param reqVO
     * @return
     */
    @Override
    public SaasWxPayOrderResVO placeOrder(SaasWxPayOrderReqVO reqVO) throws SaasWxPayException, PinganApiException {
        //生成水滴订单号
        String orderId = uid + SerialNumUtil.generateSerial();
        //水滴订单下单
        PayOrders payOrders = new PayOrders();
        payOrders.setUuid(UUID.randomUUID().toString().replace("-", ""));
        payOrders.setAmount(reqVO.getTotalMoney());
        payOrders.setCreatedAt(new Date());
        payOrders.setFeeType(reqVO.getFeeType());
        payOrders.setOrderId(orderId);
        payOrders.setPaid(0);
        payOrders.setPayType("pinganwx");
        payOrders.setServer("GATEWAY");
        payOrders.setTitle(reqVO.getAddress());
        payOrders.setToken(reqVO.getToken());
        payOrders.setUserId(reqVO.getRenterId());
        //第三方下单
        PinganWxPayOrderReqVO pinganWxPayOrderReqVO = new PinganWxPayOrderReqVO();
        pinganWxPayOrderReqVO.setOut_no(orderId);
        pinganWxPayOrderReqVO.setPmt_tag("WeixinOL");
        //原始交易金额和实际交易金额；
        int amount = (int) (reqVO.getTotalMoney() * 100);
        pinganWxPayOrderReqVO.setOriginal_amount(amount);
        pinganWxPayOrderReqVO.setTrade_amount(amount);
        pinganWxPayOrderReqVO.setRemark(FeeTypeEnum.getNameByCode(reqVO.getFeeType()));
        //支付成功的异步通知地址
        pinganWxPayOrderReqVO.setNotify_url(shuidiUrl + "saas/wx/notify");
        pinganWxPayOrderReqVO.setSub_appid("wxed1a36ce3fa969f5");
        pinganWxPayOrderReqVO.setSub_openid(reqVO.getOpenId());
        pinganWxPayOrderReqVO.setJSAPI("1");
        PinganWxPayOrderResVO pinganWxPayOrderResVO = pinganApiService.payOrder(pinganWxPayOrderReqVO);
        //第三方下单成功,更新水滴订单信息
        payOrders.setRedirectUrl(pinganWxPayOrderResVO.getOrd_no());
        //存水滴订单信息
        payOrdersDao.insert(payOrders);
        SaasWxPayOrderResVO resVO = new SaasWxPayOrderResVO();
        BeanUtils.copyProperties(pinganWxPayOrderResVO, resVO);
        return resVO;
    }
}
