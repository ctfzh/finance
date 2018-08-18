package com.ih2ome.model.caspain;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "user_profile")
public class UserProfile {

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
    @Column(name = "company")
    private String company;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "created_by_id")
    private Integer createdById;
    @Column(name = "deleted_by_id")
    private Integer deletedById;
    @Column(name = "updated_by_id")
    private Integer updatedById;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "address")
    private String address;
    @Column(name = "is_franchisee")
    private Integer isFranchisee;
    @Column(name = "is_first_login")
    private Integer isFirstLogin;
    @Column(name = "area")
    private String area;
    @Column(name = "credit_ceiling")
    private Double creditCeiling;
    @Column(name = "credit_used")
    private Double creditUsed;
    @Column(name = "id_number")
    private String idNumber;
    @Column(name = "is_test")
    private Integer isTest;
    @Column(name = "terminal")
    private String terminal;
    @Column(name = "company_postfix")
    private String companyPostfix;
    @Column(name = "url_from")
    private String urlFrom;
    @Column(name = "city")
    private String city;
    @Column(name = "company_brand")
    private String companyBrand;
    @Column(name = "province")
    private String province;
    @Column(name = "avatar_id")
    private Integer avatarId;
    @Column(name = "backups_at")
    private Date backupsAt;
    @Column(name = "email")
    private String email;
    @Column(name = "interval")
    private Integer interval;
    @Column(name = "is_freeze")
    private Integer isFreeze;
    @Column(name = "is_online")
    private Integer isOnline;
    @Column(name = "level")
    private Integer level;
    @Column(name = "is_channel")
    private Integer isChannel;
    @Column(name = "is_loan")
    private Integer isLoan;
    @Column(name = "referral")
    private String referral;
    @Column(name = "status")
    private Integer status;
    @Column(name = "vip_action")
    private Integer vipAction;
    @Column(name = "vip_expire_at")
    private Date vipExpireAt;
    @Column(name = "vip_level")
    private Integer vipLevel;
    @Column(name = "vip_status")
    private Integer vipStatus;
    @Column(name = "legal_person")
    private String legalPerson;
    @Column(name = "operate_city")
    private String operateCity;
    @Column(name = "operate_mode")
    private Integer operateMode;
    @Column(name = "idauth_type")
    private Integer idauthType;
    @Column(name = "shuidi_vip")
    private Integer shuidiVip;
    @Column(name = "is_effective")
    private String isEffective;
    @Column(name = "identity")
    private String identity;
    @Column(name = "company_logo")
    private String companyLogo;

}
