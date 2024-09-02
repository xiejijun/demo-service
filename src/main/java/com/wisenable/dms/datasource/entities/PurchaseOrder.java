package com.wisenable.dms.datasource.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PurchaseOrder implements Serializable {
    private Long id;

    private String orderNo;

    private String salesOrderNo;

    private Date orderTime;

    private String salesName;

    private Date salesOrderTime;

    private Integer orderStatus;

    private Long auditUser;

    private Long shipperId;

    private String thirdNo;

    private String trackingNo;

    private Integer workType;

    private BigDecimal totalSalePrice;

    private BigDecimal totalAmount;

    private BigDecimal discount;

    private String remark;

    private String bankInfo;

    private Long tenantId;

    private Date createTime;

    private Date updateTime;

    private String attachments;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo == null ? null : salesOrderNo.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName == null ? null : salesName.trim();
    }

    public Date getSalesOrderTime() {
        return salesOrderTime;
    }

    public void setSalesOrderTime(Date salesOrderTime) {
        this.salesOrderTime = salesOrderTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(Long auditUser) {
        this.auditUser = auditUser;
    }

    public Long getShipperId() {
        return shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }

    public String getThirdNo() {
        return thirdNo;
    }

    public void setThirdNo(String thirdNo) {
        this.thirdNo = thirdNo == null ? null : thirdNo.trim();
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo == null ? null : trackingNo.trim();
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public BigDecimal getTotalSalePrice() {
        return totalSalePrice;
    }

    public void setTotalSalePrice(BigDecimal totalSalePrice) {
        this.totalSalePrice = totalSalePrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo == null ? null : bankInfo.trim();
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments == null ? null : attachments.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PurchaseOrder other = (PurchaseOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getSalesOrderNo() == null ? other.getSalesOrderNo() == null : this.getSalesOrderNo().equals(other.getSalesOrderNo()))
            && (this.getOrderTime() == null ? other.getOrderTime() == null : this.getOrderTime().equals(other.getOrderTime()))
            && (this.getSalesName() == null ? other.getSalesName() == null : this.getSalesName().equals(other.getSalesName()))
            && (this.getSalesOrderTime() == null ? other.getSalesOrderTime() == null : this.getSalesOrderTime().equals(other.getSalesOrderTime()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getAuditUser() == null ? other.getAuditUser() == null : this.getAuditUser().equals(other.getAuditUser()))
            && (this.getShipperId() == null ? other.getShipperId() == null : this.getShipperId().equals(other.getShipperId()))
            && (this.getThirdNo() == null ? other.getThirdNo() == null : this.getThirdNo().equals(other.getThirdNo()))
            && (this.getTrackingNo() == null ? other.getTrackingNo() == null : this.getTrackingNo().equals(other.getTrackingNo()))
            && (this.getWorkType() == null ? other.getWorkType() == null : this.getWorkType().equals(other.getWorkType()))
            && (this.getTotalSalePrice() == null ? other.getTotalSalePrice() == null : this.getTotalSalePrice().equals(other.getTotalSalePrice()))
            && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
            && (this.getDiscount() == null ? other.getDiscount() == null : this.getDiscount().equals(other.getDiscount()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getBankInfo() == null ? other.getBankInfo() == null : this.getBankInfo().equals(other.getBankInfo()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getAttachments() == null ? other.getAttachments() == null : this.getAttachments().equals(other.getAttachments()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getSalesOrderNo() == null) ? 0 : getSalesOrderNo().hashCode());
        result = prime * result + ((getOrderTime() == null) ? 0 : getOrderTime().hashCode());
        result = prime * result + ((getSalesName() == null) ? 0 : getSalesName().hashCode());
        result = prime * result + ((getSalesOrderTime() == null) ? 0 : getSalesOrderTime().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getAuditUser() == null) ? 0 : getAuditUser().hashCode());
        result = prime * result + ((getShipperId() == null) ? 0 : getShipperId().hashCode());
        result = prime * result + ((getThirdNo() == null) ? 0 : getThirdNo().hashCode());
        result = prime * result + ((getTrackingNo() == null) ? 0 : getTrackingNo().hashCode());
        result = prime * result + ((getWorkType() == null) ? 0 : getWorkType().hashCode());
        result = prime * result + ((getTotalSalePrice() == null) ? 0 : getTotalSalePrice().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getDiscount() == null) ? 0 : getDiscount().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getBankInfo() == null) ? 0 : getBankInfo().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getAttachments() == null) ? 0 : getAttachments().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", salesOrderNo=").append(salesOrderNo);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", salesName=").append(salesName);
        sb.append(", salesOrderTime=").append(salesOrderTime);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", auditUser=").append(auditUser);
        sb.append(", shipperId=").append(shipperId);
        sb.append(", thirdNo=").append(thirdNo);
        sb.append(", trackingNo=").append(trackingNo);
        sb.append(", workType=").append(workType);
        sb.append(", totalSalePrice=").append(totalSalePrice);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", discount=").append(discount);
        sb.append(", remark=").append(remark);
        sb.append(", bankInfo=").append(bankInfo);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", attachments=").append(attachments);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}