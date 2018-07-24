package com.ih2ome.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.google.common.io.Resources;
import com.ih2ome.common.support.ResponseBodyVO;
import com.ih2ome.common.utils.ip.IPUtil;
import com.ih2ome.common.utils.ip.IPWhiteListUtil;
import com.ih2ome.dao.caspain.CaspainMoneyFlowDao;
import com.ih2ome.dao.caspain.ConfigPaymentsChannelDao;
import com.ih2ome.dao.volga.VolgaMoneyFlowDao;
import com.ih2ome.model.caspain.ConfigPaymentsChannel;
import com.ih2ome.model.volga.MoneyFlow;
import com.ih2ome.server.pingan.sdk.InitConfiguration;
import com.pabank.sdk.PABankSDK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @GetMapping("/one")
    @ResponseBody
    public ResponseBodyVO test01() {
        //分页
        PageHelper.startPage(0, 2);
        List<ConfigPaymentsChannel> configPaymentsChannels = configPaymentsChannelDao.selectAll();
        ConfigPaymentsChannel configPaymentsChannel = configPaymentsChannelDao.selectByPrimaryKey((Integer) 2);
        System.out.println(configPaymentsChannel);
        ResponseBodyVO responseBodyVO = new ResponseBodyVO();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("www", configPaymentsChannels);
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

}
