package com.ih2ome.model.caspain;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "terminal_token")
public class TerminalToken {

    @Id
    @Column(name = "`key`")
    private String key;
    @Column(name = "created")
    private Date created;
    @Column(name = "terminal")
    private String terminal;
    @Column(name = "user_id")
    private Integer userId;

}
