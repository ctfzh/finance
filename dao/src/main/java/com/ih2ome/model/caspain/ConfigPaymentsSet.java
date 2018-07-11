package com.ih2ome.model.caspain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "config_payments_set")
public class ConfigPaymentsSet {

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
    private Integer version = 0;
    @Column(name = "is_delete")
    private Integer isDelete = 0;
    @Column(name = "service_charge")
    private Double serviceCharge;
    @Column(name = "assume_person")
    private String assumePerson;
    @Column(name = "payments_channel_id")
    private Integer paymentsChannelId;

}
