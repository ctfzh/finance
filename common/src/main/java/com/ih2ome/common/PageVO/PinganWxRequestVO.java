package com.ih2ome.common.PageVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/07/24
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganWxRequestVO {
    //商户门店open_id
    private String open_id;
    //Unix时间戳
    private String timestamp;
    //签名
    private String sign;
    //用户提交的post请求数据
    private String data;
}
