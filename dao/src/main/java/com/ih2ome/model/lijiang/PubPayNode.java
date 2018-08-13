package com.ih2ome.model.lijiang;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "pub_pay_node")
public class PubPayNode {

    @Column(name = "node_nodecode")
    private String nodeNodecode;
    @Column(name = "node_nodename")
    private String nodeNodename;

}
