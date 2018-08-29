package com.ih2ome.common.PageVO.WebVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/08/29
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class WebWithdrawStatusResVO {
    //h2ome_trade表的trade_id
    private String tradeId;
    //提现状态(0提现中，1成功，2失败)
    private String withdrawStatus;
}
