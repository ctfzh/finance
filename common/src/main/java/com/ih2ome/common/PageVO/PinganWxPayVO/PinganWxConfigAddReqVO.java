package com.ih2ome.common.PageVO.PinganWxPayVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/07/27
 * email sky.li@ixiaoshuidi.com
 * 商户支付配置添加请求对象
 **/
@Data
public class PinganWxConfigAddReqVO {
    //支付标签
    private String pmt_tag;
    //配置关联公众号appid
    private String sub_appid;
    //支付授权目录
    private String jsapi_path;
    //配置推荐关注公众号appid
    private String subscribe_appid;
}
