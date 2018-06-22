package com.ih2ome.common.test.sdk;

import java.net.InetAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
//
//import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
//import org.eclipse.jetty.websocket.client.WebSocketClient;
import com.alibaba.fastjson.JSONObject;

//import com.pabank.bean.ConfigBean;
//import com.pabank.broadcast.SimpleEchoSocket;
import com.pabank.sdk.PABankSDK;
//import com.pabank.sdk.common.Config;

public class PushTradeInfo {

    public static void onWebSocketStr(String arg0) {
        System.out.println("广播：" + arg0);
    }

    public static void main(String[] args) {
        //初始化配置
        PABankSDK.init("./conf/config.properties");
        //验证开发者
        PABankSDK.getInstance().approveDev();

        //登录数据
        JSONObject json = new JSONObject();
        json.put("term_type", "11");
        json.put("user_key", "8888897641239412631");
        json.put("user_type", "3");
        json.put("user_id", "88888");
        json.put("branch_id", "88888");

        //连接广播服务
        Map<String, Object> broadcastData = PABankSDK.getBroadcastData(json);
        //多套配置调用方法
//		Map<String, Object> broadcastData = PABankSDK.getBroadcastData(json,"AAA");
        System.out.println(broadcastData);

    }

}
