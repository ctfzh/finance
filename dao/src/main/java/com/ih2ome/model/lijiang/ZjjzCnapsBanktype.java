package com.ih2ome.model.lijiang;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "zjjz_cnaps_banktype")
public class ZjjzCnapsBanktype {

    @Column(name = "bank_code")
    private String bankCode;
    @Column(name = "bank_name")
    private String bankName;

}
