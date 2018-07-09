package com.ih2ome.common.enums;

/**
 * @author Sky
 * create 2018/07/09
 * email sky.li@ixiaoshuidi.com
 **/
public enum ConfigPayAssumeEnum {
    //renter:×â¿Í³Ðµ££¬ landlord:·¿¶«³Ðµ£
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
