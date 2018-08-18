package com.ih2ome.model.caspain;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "employer")
public class Employer {

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
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "remark")
    private String remark;
    @Column(name = "boss_id")
    private Integer bossId;
    @Column(name = "created_by_id")
    private Integer createdById;
    @Column(name = "deleted_by_id")
    private Integer deletedById;
    @Column(name = "updated_by_id")
    private Integer updatedById;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "avatar_id")
    private Integer avatarId;

}
