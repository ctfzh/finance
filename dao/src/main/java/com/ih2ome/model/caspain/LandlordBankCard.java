package com.ih2ome.model.caspain;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "landlord_bank_card")
public class LandlordBankCard {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;
    @Column(name = "version")
    private Integer version;
    @Column(name = "is_delete")
    private Integer isDelete;
    @Column(name = "card_no")
    private String cardNo;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "owner_name")
    private String ownerName;
    @Column(name = "created_by_id")
    private Integer createdById;
    @Column(name = "deleted_by_id")
    private Integer deletedById;
    @Column(name = "updated_by_id")
    private Integer updatedById;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "branch_bank")
    private String branchBank;
    @Column(name = "used_type")
    private Integer usedType;
}
