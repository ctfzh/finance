package com.ih2ome.common.PageVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/01
 * email sky.li@ixiaoshuidi.com
 * saas微信下单返回数据封装
 **/
@Data
@ApiModel
public class SaasWxPayOrderResVO {
    @ApiModelProperty("公众号id")
    private String appId;

    @ApiModelProperty("时间戳")
    private String timeStamp;

    @ApiModelProperty("随机字符串")
    private String nonceStr;

    @ApiModelProperty("订单详情扩展字符串(package)")
    private String package_info;

    @ApiModelProperty("签名方式")
    private String signType;

    @ApiModelProperty("签名")
    private String paySign;

    @Override
    public String toString() {
        return "SaasWxPayOrderResVO{" +
                "appId='" + appId + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", package_info='" + package_info + '\'' +
                ", signType='" + signType + '\'' +
                ", paySign='" + paySign + '\'' +
                '}';
    }
}
