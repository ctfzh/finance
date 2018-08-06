package com.ih2ome.model.lijiang;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "card_id")
    private String cardId;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "server")
    private String server;
    @Column(name = "title")
    private String title;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "paid")
    private Integer paid;
    @Column(name = "pay_type")
    private String payType;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "paid_at")
    private Date paidAt;
    @Column(name = "redirect_url")
    private String redirectUrl;
    @Column(name = "token")
    private String token;
    @Column(name = "fee_type")
    private Integer feeType;
    @Column(name = "payOrder_id")
    private String payOrderId;
    @Column(name = "result")
    private String result;
    @Column(name = "saas_result")
    private String saasResult;

}
