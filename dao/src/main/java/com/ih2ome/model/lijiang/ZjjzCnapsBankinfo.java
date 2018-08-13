package com.ih2ome.model.lijiang;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "zjjz_cnaps_bankinfo")
public class ZjjzCnapsBankinfo {

    @Column(name = "bankno")
    private String bankno;
    @Column(name = "status")
    private String status;
    @Column(name = "bankclscode")
    private String bankclscode;
    @Column(name = "citycode")
    private String citycode;
    @Column(name = "bankname")
    private String bankname;

}
