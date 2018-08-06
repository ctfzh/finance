package com.ih2ome.model.lijiang;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "sub_orders")
public class SubOrders {

 @Id
 @Column(name = "uuid")
  private String uuid;
 @Column(name = "order_id")
  private String orderId;
 @Column(name = "sub_order_id")
  private String subOrderId;
 @Column(name = "sub_account")
  private String subAccount;
 @Column(name = "tran_fee")
  private Double tranFee;
 @Column(name = "sub_amount")
  private Double subAmount;
 @Column(name = "remark")
  private String remark;
 @Column(name = "raw_data")
  private String rawData;

}
