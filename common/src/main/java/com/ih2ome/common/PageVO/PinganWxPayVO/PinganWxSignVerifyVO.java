package com.ih2ome.common.PageVO.PinganWxPayVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/07/24
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganWxSignVerifyVO {
    //返回错误码，0表示没有错误
    private String errcode;
    //返回错误信息
    private String msg;
    //open_key进行aes加密后的数据
    private String data;
    //签名
    private String sign;
    //Unix时间戳（s）
    private String timestamp;
    //门店密钥
    private String open_key;

    public String buildString() {
        return "data=" + data + "&errcode=" +
                errcode + "&msg=" + msg + "&open_key=" + open_key + "&timestamp=" + timestamp;
    }

    @Override
    public String toString() {
        return "PinganWxSignVerifyVO{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                ", sign='" + sign + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", open_key='" + open_key + '\'' +
                '}';
    }
}
