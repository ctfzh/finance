package com.ih2ome.model.volga;

import java.io.Serializable;
import java.util.Date;

public class Apartment implements Serializable {
    private Integer id;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private Integer createdBy;

    private Integer updatedBy;

    private Integer deletedBy;

    private Integer version;

    private Boolean isDelete;

    private String name;

    private String city;

    private String district;

    private String block;

    private String address;

    private Boolean isNew;

    private Boolean isDecorating;

    private Date decoratingStartAt;

    private Date decoratingEndAt;

    private Short floorNum;

    private Short floorRoomNum;

    private Short roomNum;

    private Integer operatorId;

    private String area;

    private String latitude;

    private String longitude;

    private Short sourceType;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block == null ? null : block.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsDecorating() {
        return isDecorating;
    }

    public void setIsDecorating(Boolean isDecorating) {
        this.isDecorating = isDecorating;
    }

    public Date getDecoratingStartAt() {
        return decoratingStartAt;
    }

    public void setDecoratingStartAt(Date decoratingStartAt) {
        this.decoratingStartAt = decoratingStartAt;
    }

    public Date getDecoratingEndAt() {
        return decoratingEndAt;
    }

    public void setDecoratingEndAt(Date decoratingEndAt) {
        this.decoratingEndAt = decoratingEndAt;
    }

    public Short getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Short floorNum) {
        this.floorNum = floorNum;
    }

    public Short getFloorRoomNum() {
        return floorRoomNum;
    }

    public void setFloorRoomNum(Short floorRoomNum) {
        this.floorRoomNum = floorRoomNum;
    }

    public Short getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Short roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public Short getSourceType() {
        return sourceType;
    }

    public void setSourceType(Short sourceType) {
        this.sourceType = sourceType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", deletedAt=").append(deletedAt);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", deletedBy=").append(deletedBy);
        sb.append(", version=").append(version);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", name=").append(name);
        sb.append(", city=").append(city);
        sb.append(", district=").append(district);
        sb.append(", block=").append(block);
        sb.append(", address=").append(address);
        sb.append(", isNew=").append(isNew);
        sb.append(", isDecorating=").append(isDecorating);
        sb.append(", decoratingStartAt=").append(decoratingStartAt);
        sb.append(", decoratingEndAt=").append(decoratingEndAt);
        sb.append(", floorNum=").append(floorNum);
        sb.append(", floorRoomNum=").append(floorRoomNum);
        sb.append(", roomNum=").append(roomNum);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", area=").append(area);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", sourceType=").append(sourceType);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}