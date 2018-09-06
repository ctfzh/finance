package com.ih2ome.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.google.common.io.Resources;
import com.ih2ome.common.Exception.PinganMchException;
import com.ih2ome.common.Exception.PinganWxPayException;
import com.ih2ome.common.PageVO.PinganMchVO.*;
import com.ih2ome.common.PageVO.PinganWxPayVO.*;
import com.ih2ome.common.support.ResponseBodyVO;
import com.ih2ome.common.utils.ip.IPUtil;
import com.ih2ome.common.utils.ip.IPWhiteListUtil;
import com.ih2ome.common.utils.pingan.SerialNumUtil;
import com.ih2ome.dao.caspain.CaspainMoneyFlowDao;
import com.ih2ome.dao.caspain.ConfigPaymentsChannelDao;
import com.ih2ome.dao.lijiang.PayOrdersDao;
import com.ih2ome.dao.volga.VolgaMoneyFlowDao;
import com.ih2ome.model.lijiang.PayOrders;
import com.ih2ome.model.lijiang.SubAccount;
import com.ih2ome.model.lijiang.SubOrders;
import com.ih2ome.model.lijiang.ZjjzCnapsBanktype;
import com.ih2ome.model.volga.MoneyFlow;
import com.ih2ome.server.pingan.sdk.InitConfiguration;
import com.ih2ome.service.*;
import com.pabank.sdk.PABankSDK;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import javax.xml.ws.Response;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * @author Sky
 * create 2018/07/06
 * email sky.li@ixiaoshuidi.com
 **/
@RestController
@RequestMapping("/test")
public class TestMapperController {

    @Autowired
    private ConfigPaymentsChannelDao configPaymentsChannelDao;
    @Autowired
    private VolgaMoneyFlowDao volgaMoneyFlowDao;
    @Autowired
    private CaspainMoneyFlowDao caspainMoneyFlowDao;
    @Autowired
    private PinganPayService pinganPayService;
    @Autowired
    private PayOrdersDao payOrdersDao;
    @Autowired
    private PinganMchService pinganMchService;
    @Autowired
    private ZjjzCnapsBanktypeService banktypeService;
    @Autowired
    private SubAccountService subAccountService;
    @Autowired
    private WebPaymentsService webPaymentsService;


    @GetMapping("/one")
    @ResponseBody
    public ResponseBodyVO test01() {
        //分页
        PageHelper.startPage(0, 2);
        List<PayOrders> payOrders = payOrdersDao.selectAll();
        System.out.println(payOrders);
        ResponseBodyVO responseBodyVO = new ResponseBodyVO();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("www", payOrders);
        responseBodyVO.setData(jsonObject);
        responseBodyVO.setCode(0);
        responseBodyVO.setMsg("success");
        return responseBodyVO;
    }

    @GetMapping("two")
    @ResponseBody
    public ResponseBodyVO test02() {
        ResponseBodyVO responseBodyVO = new ResponseBodyVO();
        MoneyFlow volgaMoneyFlow = volgaMoneyFlowDao.selectByPrimaryKey(1);
        Example example = new Example(MoneyFlow.class);
        example.createCriteria().andEqualTo("feeType", 3);
        volgaMoneyFlowDao.selectByExample(example);
        com.ih2ome.model.caspain.MoneyFlow caspainMoneyFlow = caspainMoneyFlowDao.selectByPrimaryKey(10);
//        List<com.ih2ome.model.caspain.MoneyFlow> fee_types = caspainMoneyFlowDao.selectByExample(new Example(com.ih2ome.model.caspain.MoneyFlow.class).createCriteria().andEqualTo("feeType", 3));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("caspainMoneyFlow", caspainMoneyFlow);
        ResponseBodyVO.generateResponseObject(0, jsonObject, "success");
        responseBodyVO.setCode(0);
        responseBodyVO.setMsg("success");
        return ResponseBodyVO.generateResponseObject(0, jsonObject, "success");
    }

    @GetMapping("three")
    @ResponseBody
    public ResponseBodyVO test03(HttpServletRequest request) {
        InitConfiguration.init("test");
        String realIp = IPUtil.getIpAddr(request);
        System.out.println(realIp);
        try {
            List<String> ipList = Resources.readLines(Resources.getResource("ipWhiteList.txt"), Charset.forName("utf-8"));
            if (!IPWhiteListUtil.checkIpList(realIp, ipList)) {
                System.out.println("=================错误，错误ip" + realIp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseBodyVO.generateResponseObject(0, null, "success");
    }

    @GetMapping("four")
    @ResponseBody
    public ResponseBodyVO test04(HttpServletRequest request) throws IOException {
        // JSON请求报文，系统流水号（CnsmrSeqNo）必输，规范：6位uid(文件传输用户短号)+8位当前系统日期(YYYYMMDD)+8位随机数。
        String req = "{\"BussTypeNo\":\"100160\",\"CnsmrSeqNo\":\"12345677\",\"CorpAgreementNo\":\"Q000400269\",\"RequestSeqNo\":\"H222852018051712345678\",\"StartDate\":\"20180517\",\"EndDate\":\"20180517\",\"TranStatus\":\"0\"}";
        // b.交易测试，传入参数服务ID，JSON报文流水号字段需传入22位
        Map<String, Object> returnMap = PABankSDK.getInstance().newApiInter(req, "TranInfoQuery");
        // b.交易测试，提供流水号拼接方法，JSON报文流水号字段只需传入8位随机数
        // Map<String, Object> returnMap=PABankSDK.getInstance().newApiInter(req,"TranInfoQuery");
        System.out.println(returnMap);
        return ResponseBodyVO.generateResponseObject(0, null, "success");
    }


    @GetMapping("five")
    @ResponseBody
    public ResponseBodyVO test05(HttpServletRequest request) throws IOException, InvocationTargetException, IllegalAccessException {
        PinganWxPayListReqVO pinganWxPayListReqVO = new PinganWxPayListReqVO();
        pinganWxPayListReqVO.setPmt_type("2,3,4,5");
        try {
            List<PinganWxPayListResVO> paylist = pinganPayService.paylist(pinganWxPayListReqVO);
            System.out.println(paylist);
        } catch (PinganWxPayException e) {
            e.printStackTrace();
        }
        return ResponseBodyVO.generateResponseObject(0, null, "success");
    }


    @GetMapping("six")
    @ResponseBody
    public ResponseBodyVO test06(HttpServletRequest request) throws IOException, InvocationTargetException, IllegalAccessException {
        PinganWxOrderReqVO pinganWxOrderReqVO = new PinganWxOrderReqVO();
        try {
            PinganWxOrderResVO pinganWxOrderResVO = pinganPayService.queryOrderList(pinganWxOrderReqVO);
            System.out.println(pinganWxOrderResVO);
        } catch (PinganWxPayException e) {
            e.printStackTrace();
        }
        return ResponseBodyVO.generateResponseObject(0, null, "success");
    }


    @GetMapping("seven")
    @ResponseBody
    public ResponseBodyVO test07(HttpServletRequest request) throws IOException, InvocationTargetException, IllegalAccessException {
        PinganWxOrderViewReqVO pinganWxOrderViewReqVO = new PinganWxOrderViewReqVO();
        pinganWxOrderViewReqVO.setOut_no("20009043412018072692671423");
        try {
            PinganWxOrderViewResVO pinganWxOrderViewResVO = pinganPayService.queryOrderView(pinganWxOrderViewReqVO);
            System.out.println(pinganWxOrderViewResVO);
        } catch (PinganWxPayException e) {
            e.printStackTrace();
        }
        return ResponseBodyVO.generateResponseObject(0, null, "success");
    }

    @GetMapping("eight")
    @ResponseBody
    public ResponseBodyVO test08(HttpServletRequest request) throws IOException, InvocationTargetException, IllegalAccessException {
        PinganWxPayOrderReqVO pinganWxPayOrderReqVO = new PinganWxPayOrderReqVO();
        String serialNum = SerialNumUtil.generateSerial();
        System.out.println(serialNum);
        pinganWxPayOrderReqVO.setOut_no(serialNum);
        pinganWxPayOrderReqVO.setPmt_tag("WeixinOL");
        pinganWxPayOrderReqVO.setOrd_name("微信支付测试(服务器测试)");
        pinganWxPayOrderReqVO.setOriginal_amount(1);
        pinganWxPayOrderReqVO.setDiscount_amount(0);
        pinganWxPayOrderReqVO.setIgnore_amount(0);
        pinganWxPayOrderReqVO.setTrade_amount(1);
        pinganWxPayOrderReqVO.setRemark("下单接口");
        pinganWxPayOrderReqVO.setNotify_url("http://localhost:8085/demo111/callback.do");
        pinganWxPayOrderReqVO.setSub_appid("wxed1a36ce3fa969f5");
        pinganWxPayOrderReqVO.setSub_openid("JEQwcm9ULwfMrz5sCrrnXlAQ9k");
        pinganWxPayOrderReqVO.setJSAPI("1");
        System.out.println(pinganWxPayOrderReqVO);
        try {
            PinganWxPayOrderResVO pinganWxPayOrderResVO = pinganPayService.payOrder(pinganWxPayOrderReqVO);
            System.out.println(pinganWxPayOrderResVO);
        } catch (PinganWxPayException e) {
            e.printStackTrace();
        }
        return ResponseBodyVO.generateResponseObject(0, null, "success");
    }


    @PostMapping("nine")
    @ResponseBody
    public ResponseBodyVO test09() throws IOException, InvocationTargetException, IllegalAccessException {
        try {
//            pinganMchService.queryMemberBindInfo();
//            pinganMchService.queryTransferinfo();
//            pinganMchService.queryTranStatus("M394791808218285023854");
//            pinganMchService.registerAccount(2788);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseBodyVO.generateResponseObject(0, null, "success");
    }

    @GetMapping("readftpFileName")
    @ResponseBody
    public ResponseBodyVO test10() {
        banktypeService.insertBankType();
        return ResponseBodyVO.generateResponseObject(0, null, "成功");
    }

    @GetMapping("reconciliationFile")
    public ResponseBodyVO test11(@ApiParam("充值文件-CZ 提现文件-TX 交易文件-JY 余额文件-YE") @RequestParam(value = "fileName") String fileName,
                                 @RequestParam(value = "fileDate") String fileDate) {
        JSONObject data = new JSONObject();
        try {
            PinganMchQueryReconciliationDocResVO reconciliationDocResVO = pinganMchService.queryReconciliationFile(fileName, fileDate);
            System.out.println(JSONObject.toJSON(reconciliationDocResVO));
            data.put("file", reconciliationDocResVO);
        } catch (PinganMchException | IOException e) {
            e.printStackTrace();
            return ResponseBodyVO.generateResponseObject(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "success");
    }


    @GetMapping("wx_queryOrderDetail")
    public ResponseBodyVO test12(@RequestParam(value = "orderNo") String orderNo) {
        JSONObject data = new JSONObject();
        try {
            //查询订单明细
            PinganWxOrderViewReqVO reqVO = new PinganWxOrderViewReqVO();
            reqVO.setOut_no(orderNo);
            pinganPayService.queryOrderView(reqVO);
        } catch (PinganWxPayException e) {
            e.printStackTrace();
            return ResponseBodyVO.generateResponseObject(0, data, e.getMessage());
        }

        return ResponseBodyVO.generateResponseObject(0, data, "success");
    }

    @GetMapping(value = "wx_queryAllOrder")
    public ResponseBodyVO test12() {
        JSONObject data = new JSONObject();
        try {
            //查询订单列表
            PinganWxOrderReqVO reqVO = new PinganWxOrderReqVO();
            PinganWxOrderResVO pinganWxOrderResVO = pinganPayService.queryOrderList(reqVO);
            System.out.println(JSONObject.toJSONString(pinganWxOrderResVO));
            data.put("allOrder", pinganWxOrderResVO);
        } catch (PinganWxPayException e) {
            e.printStackTrace();
            return ResponseBodyVO.generateResponseObject(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "success");
    }

    @GetMapping(value = "queryAccountBalance")
    public ResponseBodyVO test13(@RequestParam(value = "landlordId") Integer landlordId) {
        JSONObject data = new JSONObject();
        //查询子账户余额
        try {
            SubAccount subAccount = subAccountService.findAccountByUserId(landlordId);
            PinganMchQueryBalanceResVO queryBalanceResVO = pinganMchService.queryBalance(subAccount);
            System.out.println(JSONObject.toJSON(queryBalanceResVO));
            data.put("balance", queryBalanceResVO);
        } catch (PinganMchException e) {
            e.printStackTrace();
            return ResponseBodyVO.generateResponseObject(-1, data, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseBodyVO.generateResponseObject(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "success");
    }

    @GetMapping(value = "mch_queryChargeDetail")
    public ResponseBodyVO test14(@RequestParam(value = "orderNo") String orderNo) {
        JSONObject data = new JSONObject();
        try {
            PinganMchChargeDetailResVO pinganMchChargeDetailResVO = pinganMchService.queryChargeDetail(orderNo);
            System.out.println(JSONObject.toJSON(pinganMchChargeDetailResVO));
            data.put("chargeDetail", pinganMchChargeDetailResVO);
        } catch (PinganMchException | IOException e) {
            e.printStackTrace();
            return ResponseBodyVO.generateResponseObject(-1, data, e.getMessage());
        }
        return ResponseBodyVO.generateResponseObject(0, data, "success");
    }

    @GetMapping(value = "accountRegulation")
    public ResponseBodyVO test15(@ApiParam("子订单out_no") @RequestParam("outNo") String outNo) {
        JSONObject data = new JSONObject();
        try {
            PinganMchAccRegulationReqVO reqVO = webPaymentsService.selectRegulationAccount(outNo);
            PinganMchAccRegulationResVO resVO = pinganMchService.accountRegulation(reqVO);
            System.out.println(JSONObject.toJSON(resVO));
            data.put("result", resVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBodyVO.generateResponseObject(-1, data, e.getMessage());

        }
        return ResponseBodyVO.generateResponseObject(0, data, "success");

    }

}
