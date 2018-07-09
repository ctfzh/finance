package com.ih2ome.model.caspain;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "auth_user")
public class AuthUser {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "password")
    private String password;
    @Column(name = "last_login")
    private Date lastLogin;
    @Column(name = "is_superuser")
    private Integer isSuperuser;
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "is_staff")
    private Integer isStaff;
    @Column(name = "is_active")
    private Integer isActive;
    @Column(name = "date_joined")
    private Date dateJoined;

}
