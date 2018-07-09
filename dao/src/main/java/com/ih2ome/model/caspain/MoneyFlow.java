package com.ih2ome.model.caspain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "money_flow")
public class MoneyFlow {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "is_delete")
    private Integer isDelete;
    @Column(name = "flow_type")
    private Integer flowType;
    @Column(name = "fee_type")
    private Integer feeType;
    @Column(name = "real_amount")
    private Double realAmount;
    @Column(name = "ought_amount")
    private Double oughtAmount;
    @Column(name = "trade_no")
    private String tradeNo;
    @Column(name = "trade_code")
    private String tradeCode;
    @Column(name = "pay_method")
    private Integer payMethod;
    @Column(name = "source_from")
    private Integer sourceFrom;
    @Column(name = "order_uuid")
    private String orderUuid;
    @Column(name = "is_whole_house")
    private Integer isWholeHouse;
    @Column(name = "trade_at")
    private Date tradeAt;
    @Column(name = "trader_name")
    private String traderName;
    @Column(name = "remark")
    private String remark;
    @Column(name = "created_by_id")
    private Integer createdById;
    @Column(name = "house_id")
    private Integer houseId;
    @Column(name = "room_id")
    private Integer roomId;
    @Column(name = "deleted_at")
    private Date deletedAt;
    @Column(name = "deleted_by_id")
    private Integer deletedById;
    @Column(name = "deleted_type")
    private Integer deletedType;
    @Column(name = "trade_serial_no")
    private String tradeSerialNo;
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "management_id")
    private Integer managementId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public Double getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Double realAmount) {
        this.realAmount = realAmount;
    }

    public Double getOughtAmount() {
        return oughtAmount;
    }

    public void setOughtAmount(Double oughtAmount) {
        this.oughtAmount = oughtAmount;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(Integer sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid;
    }

    public Integer getIsWholeHouse() {
        return isWholeHouse;
    }

    public void setIsWholeHouse(Integer isWholeHouse) {
        this.isWholeHouse = isWholeHouse;
    }

    public Date getTradeAt() {
        return tradeAt;
    }

    public void setTradeAt(Date tradeAt) {
        this.tradeAt = tradeAt;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getDeletedById() {
        return deletedById;
    }

    public void setDeletedById(Integer deletedById) {
        this.deletedById = deletedById;
    }

    public Integer getDeletedType() {
        return deletedType;
    }

    public void setDeletedType(Integer deletedType) {
        this.deletedType = deletedType;
    }

    public String getTradeSerialNo() {
        return tradeSerialNo;
    }

    public void setTradeSerialNo(String tradeSerialNo) {
        this.tradeSerialNo = tradeSerialNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getManagementId() {
        return managementId;
    }

    public void setManagementId(Integer managementId) {
        this.managementId = managementId;
    }
}
