package com.ih2ome.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.Exception.PinganMchException;
import com.ih2ome.common.Exception.PinganWxPayException;
import com.ih2ome.common.Exception.SaasWxPayException;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchAccSupplyResVO;
import com.ih2ome.common.PageVO.PinganMchVO.PinganMchChargeDetailResVO;
import com.ih2ome.common.PageVO.SaasWxNotifyReqVO;
import com.ih2ome.common.PageVO.SaasWxPayOrderReqVO;
import com.ih2ome.common.PageVO.SaasWxPayOrderResVO;
import com.ih2ome.common.support.ResponseBodyVO;
import com.ih2ome.model.lijiang.Orders;
import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.model.lijiang.SubOrders;
import com.ih2ome.service.*;
import com.sun.prism.impl.BaseMesh;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.geom.FlatteningPathIterator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author Sky
 * create 2018/08/01
 * email sky.li@ixiaoshuidi.com
 **/
@Api(description = "Saas微信支付接口")
@RestController
@RequestMapping("/saas/wx")
@CrossOrigin
public class SaasWxPayController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaasWxPayController.class);

    @Autowired
    private SaasWxPayService saasWxPayService;

    @Autowired
    private SubAccountService subAccountService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private SubOrdersService subOrdersService;

    @Autowired
    private PinganMchService pinganMchService;


    @PostMapping(value = "placeOrder", produces = "application/json;charset=UTF-8")
    @ApiOperation("微信下单接口")
    public ResponseBodyVO placeOrder(@RequestBody @Valid SaasWxPayOrderReqVO reqVO, BindingResult bindingResult) {
        JSONObject data = new JSONObject();
        LOGGER.info("placeOrder--->请求参数:{}", reqVO.toString());
        //验证参数
        if (bindingResult.hasErrors()) {
            return ResponseBodyVO.generateResponseObject(-1, data, "参数异常");
        }
        try {
            //根据主账号id查询平安商户子账户对象
//            SubAccount subAccount = subAccountService.findAccountByUserId(Integer.valueOf(reqVO.getLandlordId()));
            SubAccount subAccount = subAccountService.findAccountByUserId(2984);
            SaasWxPayOrderResVO resVO = saasWxPayService.placeOrder(reqVO, subAccount);
            data.put("payInfo", resVO);
        } catch (SaasWxPayException | UnsupportedEncodingException e) {
            e.printStackTrace();
            LOGGER.error("placeOrder--->水滴下单失败,请求参数:{},失败原因:{}", reqVO.toString(), e.getMessage());
            return ResponseBodyVO.generateResponseObject(-1, data, e.getMessage());
        } catch (PinganWxPayException e) {
            e.printStackTrace();
            LOGGER.error("placeOrder--->平安下单失败,请求参数:{},信息:{}", reqVO.toString(), e.getMessage());
            return ResponseBodyVO.generateResponseObject(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "下单成功");
    }


    @PostMapping(value = "notify", produces = "application/json;charset=UTF-8")
    @ApiOperation("支付成功回调")
    public String notify(SaasWxNotifyReqVO saasWxNotifyReqVO) {
        LOGGER.info("notify--->平安微信支付回调参数:{}", JSONObject.toJSONString(saasWxNotifyReqVO));
        Boolean flag = saasWxPayService.notify(saasWxNotifyReqVO);
        String info = "notify_error";
        if (flag) {
            info = "notify_success";
//            try {
//                LOGGER.info("notify--->总订单号:{}", saasWxNotifyReqVO.getOut_no());
//                Orders orders = ordersService.findOrdersByOrderId(saasWxNotifyReqVO.getOut_no());
//                LOGGER.info("notify--->总订单信息:{}", orders);
//                String ordersUuid = orders.getUuid();
//                LOGGER.info("notify--->总订单uuid:{}", ordersUuid);
//                SubOrders subOrders = subOrdersService.findSubOrdersByOrderId(ordersUuid);
//                LOGGER.info("notify--->子订单信息:{}", subOrders);
//                String subOrderId = subOrders.getSubOrderId();
//                //查询子订单是否已入账到对应子账户
//                PinganMchChargeDetailResVO pinganMchChargeDetailResVO = pinganMchService.queryChargeDetail(subOrderId);
//                LOGGER.info("notify--->子订单是否入商户子账户:{}", JSONObject.toJSON(pinganMchChargeDetailResVO));
//                //子订单未记账到对应子账户
//                if (pinganMchChargeDetailResVO.getTxnReturnCode().equals("ERR020")) {
//                    //调用补帐接口(总订单号)
//                    PinganMchAccSupplyResVO pinganMchAccSupplyResVO = pinganMchService.accountSupply(orders.getOrderId(), String.valueOf(orders.getAmount()));
//                    LOGGER.info("notify--->补帐请求参数:{}、{},返回报文:{}", orders.getOrderId(), orders.getAmount(), JSONObject.toJSON(pinganMchAccSupplyResVO));
//                }
//            } catch (PinganMchException | IOException e) {
//                e.printStackTrace();
//                LOGGER.error("notify--->查询订单明细,补帐失败,失败原因:{}", e.getMessage());
//                return info;
//            }
        }
        return info;
    }
}
