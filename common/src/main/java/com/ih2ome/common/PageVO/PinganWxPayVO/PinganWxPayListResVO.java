package com.ih2ome.common.PageVO.PinganWxPayVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/07/26
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganWxPayListResVO {
    //收单机构名称
    private String pmt_name;
    //收单机构内部名称
    private String pmt_internal_name;
    //支付类型（0现金，1刷卡（拍卡），2主扫，3被扫）,可能含有多个条件组合。多个以小写逗号分开
    private String pmt_type;
    //图标
    private String pmt_icon;
    //小票图标
    private String pmt_ticket_icon;
    //支付标签
    private String pmt_tag;
    //支持币种
    private String pmt_currency;
    //是否支持多笔退款（1可以，0不可以）
    private String pmt_split_refund;
    //签购单名称
    private String ticket_name;

    @Override
    public String toString() {
        return "PinganWxPayListResVO{" +
                "pmt_name='" + pmt_name + '\'' +
                ", pmt_internal_name='" + pmt_internal_name + '\'' +
                ", pmt_type='" + pmt_type + '\'' +
                ", pmt_icon='" + pmt_icon + '\'' +
                ", pmt_ticket_icon='" + pmt_ticket_icon + '\'' +
                ", pmt_tag='" + pmt_tag + '\'' +
                ", pmt_currency='" + pmt_currency + '\'' +
                ", pmt_split_refund='" + pmt_split_refund + '\'' +
                ", ticket_name='" + ticket_name + '\'' +
                '}';
    }
}
