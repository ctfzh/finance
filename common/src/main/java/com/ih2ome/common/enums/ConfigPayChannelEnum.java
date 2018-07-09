package com.ih2ome.common.enums;

/**
 * @author Sky
 * create 2018/07/09
 * email sky.li@ixiaoshuidi.com
 **/
public enum ConfigPayChannelEnum {
    ALLIANPAY_WX("allianpay_wx"),
    ALIPAY("alipay"),
    LLIANPAY_CARD("llianpay_card"),
    PINGANPAY_WX("pinganpay_wx");

    private String name;

    ConfigPayChannelEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
