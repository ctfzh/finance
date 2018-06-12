package com.ih2ome.common.utils.BaiDuMap;

import com.alibaba.fastjson.JSONObject;
import com.ih2ome.common.utils.client.HttpClientUtil;

/**
 * @author Sky
 * create 2018/05/28
 * email sky.li@ixiaoshuidi.com
 **/
//百度地图计算两个坐标点的工具类
public class BaiDuMapUtils {
    //百度地图的AK
    private static final String AK = "jmaA6VO4tKaOD943RARHdGL6wEeP4jCV";
    //地球半径
    private static double EARTH_RADIUS = 6378.137;

    //根据经纬度坐标判断距离
    public static Double getDistanceFromHttp(String origins, String destinations) {
        String url = "http://api.map.baidu.com/routematrix/v2/walking?output=json&ak=" + AK + "&origins=" + origins + "&destinations=" + destinations;
        String result = HttpClientUtil.doGet(url);
        JSONObject resJson = JSONObject.parseObject(result);
        Double distance = 0.0;
        if (resJson != null) {
            JSONObject data = (JSONObject) resJson.getJSONArray("result").get(0);
            Double value = data.getJSONObject("distance").getDouble("value");
            distance = value;
        }
        return distance;
    }

    /**
     * 通过经纬度获取距离(单位：公里)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistanceFromLocal(double lat1, double lng1, double lat2,
                                              double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
}
