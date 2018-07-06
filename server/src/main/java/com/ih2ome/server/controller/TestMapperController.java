package com.ih2ome.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.support.ResponseBodyVO;
import com.ih2ome.dao.caspain.ConfigPaymentsChannelDao;
import com.ih2ome.model.caspain.ConfigPaymentsChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/one")
    @ResponseBody
    public ResponseBodyVO test() {
        List<ConfigPaymentsChannel> configPaymentsChannels = configPaymentsChannelDao.selectAll();
        ConfigPaymentsChannel configPaymentsChannel = configPaymentsChannelDao.selectByPrimaryKey((Integer)2);
        System.out.println(configPaymentsChannel);
        ResponseBodyVO responseBodyVO = new ResponseBodyVO();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("www", configPaymentsChannels);
        responseBodyVO.setData(jsonObject);
        responseBodyVO.setCode(0);
        responseBodyVO.setMsg("³É¹¦");
        return responseBodyVO;
    }

}
