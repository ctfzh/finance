package com.ih2ome.model.caspain;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "config_payments_user")
public class ConfigPaymentsUser {

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
    @Column(name = "user_type")
    private Integer userType;
    @Column(name = "wx_show")
    private Integer wxShow;
    @Column(name = "ali_show")
    private Integer aliShow;
    @Column(name = "card_show")
    private Integer cardShow;
    @Column(name = "wx_type")
    private String wxType;
    @Column(name = "ali_type")
    private String aliType;
    @Column(name = "card_type")
    private String cardType;
}
