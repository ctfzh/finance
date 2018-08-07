package com.ih2ome.common.PageVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/02
 * email sky.li@ixiaoshuidi.com
 * 支付成功回调请求参数
 **/
@Data
@ApiModel
public class SaasWxNotifyReqVO {
    @ApiModelProperty("商户门店open_id")
    private String open_id;

    @ApiModelProperty("订单号")
    private String ord_no;

    @ApiModelProperty("Unix时间戳")
    private String timestamp;

    @ApiModelProperty("随机字符串")
    private String rand_str;

    @ApiModelProperty("开发者交易流水号（订单号）")
    private String out_no;

    @ApiModelProperty("订单状态（1支付成功，4取消）")
    private String status;

    @ApiModelProperty("交易金额（分为单位）")
    private String amount;

    @ApiModelProperty("收单机构原始交易数据")
    private String trade_result;

    @ApiModelProperty("交易成功时间（yyyymmddhhiiss）")
    private String pay_time;

    @ApiModelProperty("签名")
    private String sign;

    @Override
    public String toString() {
        return "SaasWxNotifyReqVO{" +
                "open_id='" + open_id + '\'' +
                ", ord_no='" + ord_no + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", rand_str='" + rand_str + '\'' +
                ", out_no='" + out_no + '\'' +
                ", status='" + status + '\'' +
                ", amount='" + amount + '\'' +
                ", trade_result='" + trade_result + '\'' +
                ", pay_time='" + pay_time + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
