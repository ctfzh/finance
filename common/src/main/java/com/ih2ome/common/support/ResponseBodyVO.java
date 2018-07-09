package com.ih2ome.common.support;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import sun.awt.SunHints;

/**
 * @author Sky
 * create 2018/04/26
 * email sky.li@ixiaoshuidi.com
 **/
@Data
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

    public static ResponseBodyVO generateResponseObject(Integer code, JSONObject data, String msg) {
        ResponseBodyVO responseBodyVO = new ResponseBodyVO();
        responseBodyVO.setCode(code);
        responseBodyVO.setMsg(msg);
        responseBodyVO.setData(data);
        return responseBodyVO;
    }


}

