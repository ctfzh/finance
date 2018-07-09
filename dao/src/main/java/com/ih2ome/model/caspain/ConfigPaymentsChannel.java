package com.ih2ome.model.caspain;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "config_payments_channel")
public class ConfigPaymentsChannel {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;
    @Column(name = "created_by_id")
    private Integer createdById;
    @Column(name = "updated_by_id")
    private Integer updatedById;
    @Column(name = "deleted_by_id")
    private Integer deletedById;
    @Column(name = "version")
    private Integer version;
    @Column(name = "is_delete")
    private Integer isDelete;
    @Column(name = "pay_channel")
    private String payChannel;
    @Column(name = "default_charge")
    private Double defaultCharge;

}
