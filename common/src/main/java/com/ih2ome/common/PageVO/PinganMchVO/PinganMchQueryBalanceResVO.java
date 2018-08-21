package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author Sky
 * create 2018/08/20
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchQueryBalanceResVO extends PinganMchBaseResVO {
    //本次交易返回查询结果记录数
    private String ResultNum;

    //起始记录号
    private String StartRecordNo;

    //结束标志(0：否  1：是)
    private String EndFlag;

    //符合业务查询条件的记录总数
    private String TotalNum;

    //保留域
    private String ReservedMsg;

    //账户信息数组
    private List<PinganMchQueryBalanceAcctArray> AcctArray;

}
