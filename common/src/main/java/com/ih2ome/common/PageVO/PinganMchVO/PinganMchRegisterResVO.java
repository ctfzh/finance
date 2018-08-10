package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/10
 * email sky.li@ixiaoshuidi.com
 * 会员子账户开户返回对象
 **/
@Data
public class PinganMchRegisterResVO {
    //子账户账号
    private String SubAcctNo;
    //保留域
    private String ReservedMsg;
}
