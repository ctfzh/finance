package com.ih2ome.model.caspain;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "config_payments_channel")
public class ConfigPaymentsChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private Integer createdById;
    private Integer updatedById;
    private Integer deletedById;
    private Integer version;
    private Integer isDelete;
    private String payChannel;
    private Double defaultCharge;

}
