package com.ih2ome.common.support;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Sky
 * create 2018/04/26
 * email sky.li@ixiaoshuidi.com
 **/
public class ResponseBodyVO {
    private Integer code;
    private JSONObject data;
    private String msg;

    public ResponseBodyVO() {
    }

    public ResponseBodyVO(Integer code, JSONObject data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

