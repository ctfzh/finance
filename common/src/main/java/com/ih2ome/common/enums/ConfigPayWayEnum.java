package com.ih2ome.common.enums;

/**
 * @author Sky
 * create 2018/07/11
 * email sky.li@ixiaoshuidi.com
 **/
public enum ConfigPayWayEnum {
    //WX:微信支付, ALI:支付宝支付, CARD:快捷支付
    WX("wx"), ALI("ali"), CARD("card");

    private String name;

    ConfigPayWayEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
