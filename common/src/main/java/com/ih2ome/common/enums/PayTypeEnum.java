package com.ih2ome.common.enums;

/**
 * @author Sky
 * create 2018/08/03
 * email sky.li@ixiaoshuidi.com
 * 支付类型
 **/
public enum PayTypeEnum {
    PINGANWX("pinganwx");
    private String name;

    PayTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
