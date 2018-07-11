package com.ih2ome.common.enums;

/**
 * @author Sky
 * create 2018/07/09
 * email sky.li@ixiaoshuidi.com
 **/
public enum ConfigPayAssumeEnum {
    //renter:租客承担 landlord:房东承担
    RENTER("renter"), LANDLORD("landlord");
    private String name;

    ConfigPayAssumeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
