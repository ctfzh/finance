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

}
