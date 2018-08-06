package com.ih2ome.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.Exception.PinganApiException;
import com.ih2ome.common.Exception.SaasWxPayException;
import com.ih2ome.common.PageVO.*;
import com.ih2ome.common.enums.FeeTypeEnum;
import com.ih2ome.common.enums.PayTypeEnum;
import com.ih2ome.common.utils.pingan.HttpUtil;
import com.ih2ome.common.utils.pingan.SerialNumUtil;
import com.ih2ome.common.utils.pingan.SignUtil;
import com.ih2ome.dao.lijiang.OrdersDao;
import com.ih2ome.dao.lijiang.PayOrdersDao;
import com.ih2ome.dao.lijiang.SubOrdersDao;
import com.ih2ome.model.lijiang.Orders;
import com.ih2ome.model.lijiang.PayOrders;
import com.ih2ome.model.lijiang.SubOrders;
import com.ih2ome.model.lijiang.SubPayOrders;
import com.ih2ome.service.PinganApiService;
import com.ih2ome.service.SaasWxPayService;
import org.apache.xpath.operations.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import com.ih2ome.common.PageVO.PinganWxPayOrderResVO;

import java.util.*;

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
    private OrdersDao ordersDao;
    @Autowired
    private SubOrdersDao subOrdersDao;

    //平安第三方下单
    @Autowired
    private PinganApiService pinganApiService;

    private String saasNotify = " http://pay.shuidiguanjia.com/pinganpay/wxnotify";

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
        Orders orders = new Orders();
        orders.setUuid(UUID.randomUUID().toString().replace("-", ""));
        orders.setAmount(reqVO.getTotalMoney());
        orders.setCreatedAt(new Date());
        orders.setFeeType(reqVO.getFeeType());
        orders.setOrderId(orderId);
        orders.setPaid(0);
        orders.setPayType(PayTypeEnum.PINGANWX.getName());
        orders.setServer("GATEWAY");
        orders.setTitle(reqVO.getAddress());
        orders.setToken(reqVO.getToken());
        orders.setPayOrderId(reqVO.getPayOrderId());

        //水滴子订单下单
//        SubOrders subOrders = new SubOrders();
//        subOrders.setUuid(UUID.randomUUID().toString().replace("-", ""));
//        subOrders.setOrderId(orders.getUuid());
//        subOrders.setSubOrderId(uid + SerialNumUtil.generateSerial());
//        subOrders.setSubAccount("shuidi");
//        subOrders.setSubAmount(reqVO.getTotalMoney());
//        subOrders.setTranFee(reqVO.getPayCharge());
//        subOrders.setRemark(FeeTypeEnum.getNameByCode(reqVO.getFeeType()));
//        subOrders.setRawData(JSONObject.toJSONString(subOrders));


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
//        ArrayList<PinganWxPayOrderSubDataVO> orderLists = new ArrayList<PinganWxPayOrderSubDataVO>();
//        //订单数据
//        PinganWxPayOrderSubDataVO pinganWxPayOrderSubDataVO = new PinganWxPayOrderSubDataVO();
//        pinganWxPayOrderSubDataVO.setObject(FeeTypeEnum.getNameByCode(reqVO.getFeeType()));
//        //0-冻结支付 ，1-普通支付
//        pinganWxPayOrderSubDataVO.setPayModel("1");
//        pinganWxPayOrderSubDataVO.setSubAccNo("商户名称");
//        pinganWxPayOrderSubDataVO.setSubamount(String.valueOf(subOrders.getSubAmount()));
//        pinganWxPayOrderSubDataVO.setSuborderId(subOrders.getSubOrderId());
//        pinganWxPayOrderSubDataVO.setObject(subOrders.getRemark());
//        pinganWxPayOrderSubDataVO.setTranFee(String.valueOf(subOrders.getTranFee()));
//        orderLists.add(pinganWxPayOrderSubDataVO);
//        pinganWxPayOrderSubVO.setOrderList(orderLists);
//        pinganWxPayOrderReqVO.setCmd(JSONObject.toJSONString(pinganWxPayOrderSubVO));

        PinganWxPayOrderResVO pinganWxPayOrderResVO = pinganApiService.payOrder(pinganWxPayOrderReqVO);
        //第三方下单成功,更新水滴订单信息
        orders.setRedirectUrl(pinganWxPayOrderResVO.getOrd_no());
        //存水滴订单信息
        ordersDao.insert(orders);
        //存水滴子订单信息
//        subOrdersDao.insert(subOrders);
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
        Example example = new Example(Orders.class);
        example.createCriteria().andEqualTo("orderId", outNo);
        Orders order = ordersDao.selectOneByExample(example);
        order.setResult(JSONObject.toJSONString(saasWxNotifyReqVO));
        TreeMap<String, String> params = new TreeMap<>();
        params.put("cusorderid", order.getPayOrderId());
        params.put("trxid", outNo);
        //验证签名成功，修改订单状态
        if (bool) {
            params.put("result", "success");
            //通知Saas微信端
            String saasResult = HttpUtil.httpMethodPost(saasNotify, params, null);
            order.setSaasResult(saasResult);
            //支付状态(0未支付，1已支付)
            order.setPaid(1);
            String timestamp = saasWxNotifyReqVO.getTimestamp();
            order.setPaidAt(new Date(Long.valueOf(timestamp) * 1000));
        } else {
            params.put("result", "error");
            //通知Saas微信端
            String saasResult = HttpUtil.httpMethodPost(saasNotify, params, null);
            order.setSaasResult(saasResult);
        }
        ordersDao.updateByPrimaryKey(order);
        return bool;
    }
}
