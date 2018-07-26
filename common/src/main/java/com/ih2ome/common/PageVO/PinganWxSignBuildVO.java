package com.ih2ome.common.PageVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/07/24
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganWxSignBuildVO {
    //用户提交的post请求数据
    private String data;
    //商户门店open_id
    private String open_id;
    //商户门店open_key
    private String open_key;
    //unix时间戳(s)
    private String timestamp;

    public PinganWxSignBuildVO() {
    }

    public PinganWxSignBuildVO(String data, String open_id, String open_key, String timestamp) {
        this.data = data;
        this.open_id = open_id;
        this.open_key = open_key;
        this.timestamp = timestamp;
    }

    public String buildString() {
        return "data=" + data + "&open_id=" +
                open_id + "&open_key=" + open_key + "&timestamp=" + timestamp;
    }

    @Override
    public String toString() {
        return "PinganWxSignBuildVO{" +
                "data='" + data + '\'' +
                ", open_id='" + open_id + '\'' +
                ", open_key='" + open_key + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
