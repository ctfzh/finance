package com.ih2ome.common.PageVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/13
 * email sky.li@ixiaoshuidi.com
 **/
@Data
@ApiModel("大小额银联号")
public class WebSearchCnapsVO {
    @ApiModelProperty("大小额行号")
    private String cnapsCode;
    @ApiModelProperty("银行名称")
    private String cnapsName;
}
