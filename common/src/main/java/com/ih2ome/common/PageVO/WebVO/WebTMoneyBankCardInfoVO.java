package com.ih2ome.common.PageVO.WebVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/18
 * email sky.li@ixiaoshuidi.com
 **/
@Data
@ApiModel("用户提现绑定银行卡信息")
public class WebTMoneyBankCardInfoVO {
    @ApiModelProperty("银行卡所在银行名称")
    private String bankName;
    @ApiModelProperty("银行卡卡号")
    private String bankNo;
    @ApiModelProperty("用户登录手机号")
    private String userMobile;

}
