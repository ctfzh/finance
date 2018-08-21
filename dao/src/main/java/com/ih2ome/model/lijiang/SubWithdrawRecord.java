package com.ih2ome.model.lijiang;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "sub_withdraw_record")
public class SubWithdrawRecord {
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
    @Column(name = "landlord_id")
    private Integer landlordId;
    @Column(name = "sub_account_id")
    private Integer subAccountId;
    @Column(name = "sub_account_card_id")
    private Integer subAccountCardId;
    @Column(name = "currency")
    private String currency;
    @Column(name = "withdraw_money")
    private Double withdrawMoney;
    @Column(name = "withdraw_charge")
    private Double withdrawCharge;
    @Column(name = "withdraw_status")
    private Integer withdrawStatus;
    @Column(name = "serial_no")
    private String serialNo;

}
