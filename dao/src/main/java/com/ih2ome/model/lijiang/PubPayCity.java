package com.ih2ome.model.lijiang;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "pub_pay_city")
public class PubPayCity {

    @Column(name = "city_areacode")
    private String cityAreacode;
    @Column(name = "city_areaname")
    private String cityAreaname;
    @Column(name = "city_areatype")
    private String cityAreatype;
    @Column(name = "city_nodecode")
    private String cityNodecode;
    @Column(name = "city_topareacode1")
    private String cityTopareacode1;
    @Column(name = "city_topareacode2")
    private String cityTopareacode2;
    @Column(name = "city_topareacode3")
    private String cityTopareacode3;
    @Column(name = "city_oraareacode")
    private String cityOraareacode;

}
