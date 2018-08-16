package com.ih2ome.model.lijiang;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "sub_account_card")
public class SubAccountCard {

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
    @Column(name = "is_bind")
    private Integer isBind;
    @Column(name = "cnaps_no")
    private String cnapsNo;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_no")
    private String bankNo;
    @Column(name = "bind_mobile")
    private String bindMobile;
    @Column(name = "id_card_no")
    private String idCardNo;
    @Column(name = "id_card_name")
    private String idCardName;
    @Column(name = "sub_account_id")
    private Integer subAccountId;

}
