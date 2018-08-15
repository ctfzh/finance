package com.ih2ome.common.PageVO.WebVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/15
 * email sky.li@ixiaoshuidi.com
 **/
@Data
@ApiModel("水滴注册商户子账号返回对象")
public class WebRegisterResVO {
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("用户子账号")
    private String subAccountNo;
}
