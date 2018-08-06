package com.ih2ome.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.Exception.PinganApiException;
import com.ih2ome.common.Exception.SaasWxPayException;
import com.ih2ome.common.PageVO.*;
import com.ih2ome.common.enums.FeeTypeEnum;
import com.ih2ome.common.enums.PayTypeEnum;
import com.ih2ome.common.utils.pingan.SerialNumUtil;
import com.ih2ome.common.utils.pingan.SignUtil;
import com.ih2ome.dao.lijiang.PayOrdersDao;
import com.ih2ome.model.lijiang.PayOrders;
import com.ih2ome.model.lijiang.SubPayOrders;
import com.ih2ome.service.PinganApiService;
import com.ih2ome.service.SaasWxPayService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import com.ih2ome.common.PageVO.PinganWxPayOrderResVO;

import java.util.Date;
import java.util.List;
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
    //商户门店的唯一标识
    @Value("${pingan.wxPay.open_id}")
    private String open_id;
    @Value("${pingan.wxPay.open_key}")
    private String open_key;

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
        //水滴总订单下单
        PayOrders payOrders = new PayOrders();
        payOrders.setUuid(UUID.randomUUID().toString().replace("-", ""));
        payOrders.setAmount(reqVO.getTotalMoney());
        payOrders.setCreatedAt(new Date());
        payOrders.setFeeType(reqVO.getFeeType());
        payOrders.setOrderId(orderId);
        payOrders.setPaid(0);
        payOrders.setPayType(PayTypeEnum.PINGANWX.getName());
        payOrders.setServer("GATEWAY");
        payOrders.setTitle(reqVO.getAddress());
        payOrders.setToken(reqVO.getToken());
        payOrders.setUserId(reqVO.getRenterId());

        //水滴子订单下单
//        SubPayOrders subPayOrders = new SubPayOrders();
//        subPayOrders.setUuid(UUID.randomUUID().toString().replace("-", ""));
//        subPayOrders.setOrderId(payOrders.getUuid());
//        subPayOrders.setSubOrderId(uid + SerialNumUtil.generateSerial());
//        subPayOrders.setSubAccount("shuidi");
//        subPayOrders.setSubAmount(reqVO.getTotalMoney());
//        subPayOrders.setTranFee(reqVO.getPayCharge());
//        subPayOrders.setRemark(FeeTypeEnum.getNameByCode(reqVO.getFeeType()));
//        subPayOrders.setRawData(JSONObject.toJSONString(subPayOrders));


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


        //子订单信息拼装
//        PinganWxPayOrderSubVO pinganWxPayOrderSubVO = new PinganWxPayOrderSubVO();
//        pinganWxPayOrderSubVO.setSFJOrdertype("1");
//        pinganWxPayOrderSubVO.setRemarktype("JHS0100000");
//        pinganWxPayOrderSubVO.setPlantCode("平台代码");
//        //订单数据
//        PinganWxPayOrderSubDataVO pinganWxPayOrderSubDataVO = new PinganWxPayOrderSubDataVO();
//        pinganWxPayOrderSubDataVO.setObject(FeeTypeEnum.getNameByCode(reqVO.getFeeType()));
//        //0-冻结支付 ，1-普通支付
//        pinganWxPayOrderSubDataVO.setPayModel("1");
//        pinganWxPayOrderSubDataVO.setSubAccNo("商户名称");
//        pinganWxPayOrderSubDataVO.setSubamount(String.valueOf(subPayOrders.getSubAmount()));
//        pinganWxPayOrderSubDataVO.setSuborderId(subPayOrders.getSubOrderId());
//        pinganWxPayOrderSubDataVO.setObject(subPayOrders.getRemark());
//        pinganWxPayOrderSubDataVO.setTranFee(String.valueOf(subPayOrders.getTranFee()));
//        pinganWxPayOrderReqVO.setCmd(JSONObject.toJSONString(pinganWxPayOrderSubDataVO));

        PinganWxPayOrderResVO pinganWxPayOrderResVO = pinganApiService.payOrder(pinganWxPayOrderReqVO);
        //第三方下单成功,更新水滴订单信息
        payOrders.setRedirectUrl(pinganWxPayOrderResVO.getOrd_no());
        //存水滴订单信息
        payOrdersDao.insert(payOrders);
        SaasWxPayOrderResVO resVO = new SaasWxPayOrderResVO();
        BeanUtils.copyProperties(pinganWxPayOrderResVO, resVO);
        return resVO;
    }

    /**
     * 支付成功回调
     *
     * @param saasWxNotifyReqVO
     * @return
     */
    @Override
    public Boolean notify(SaasWxNotifyReqVO saasWxNotifyReqVO) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(saasWxNotifyReqVO));
        jsonObject.put("open_key", open_key);
        Boolean bool = SignUtil.vertifySign(jsonObject);
        //获取水滴订单号
        String outNo = saasWxNotifyReqVO.getOut_no();
        //验证签名成功，修改订单状态
        if (bool) {
            Example example = new Example(PayOrders.class);
            example.createCriteria().andEqualTo("orderId", outNo);
            List<PayOrders> payOrders = payOrdersDao.selectByExample(example);
            for (PayOrders payOrder : payOrders) {
                //支付状态(0未支付，1已支付)
                payOrder.setPaid(1);
                String timestamp = saasWxNotifyReqVO.getTimestamp();
                payOrder.setPaidAt(new Date(Long.valueOf(timestamp) * 1000));
                payOrdersDao.updateByPrimaryKey(payOrder);
            }
        }
        return bool;
    }
}
