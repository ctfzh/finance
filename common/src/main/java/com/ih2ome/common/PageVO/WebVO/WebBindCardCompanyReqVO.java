package com.ih2ome.common.PageVO.WebVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Sky
 * create 2018/08/16
 * email sky.li@ixiaoshuidi.com
 **/
@Data
@ApiModel("企业绑卡请求对象")
public class WebBindCardCompanyReqVO {

    @ApiModelProperty("用户Id")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @ApiModelProperty("用户姓名")
    @NotNull(message = "用户姓名不能为空")
    private String userName;

    @ApiModelProperty("身份证号码")
    @NotNull(message = "身份证号码不能为空")
    private String idCardNo;

    @ApiModelProperty("银行卡卡号")
    @NotNull(message = "银行卡卡号不能为空")
    private String bankCardNo;

    @ApiModelProperty("开户行名称")
    @NotNull(message = "开户行名称不能为空")
    private String bankName;

    @ApiModelProperty("大小额行号")
    @NotNull(message = "大小额行号不能为空")
    private String bankCnapsNo;

    @ApiModelProperty("超级网银号")
    private String bankSupNo;

    @ApiModelProperty("手机号码")
    @NotNull(message = "手机号码不能为空")
    private String mobile;

    @ApiModelProperty("银行卡验证金额")
    private String vertifyAmount;

}
