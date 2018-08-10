package com.ih2ome.model.lijiang;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "sub_account")
public class SubAccount {

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
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "account")
    private String account;

}
