package com.wisenable.dms.datasource.entities;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;

    private String username;

    private String loginName;

    private String password;

    private String leaderFlag;

    private String position;

    private String department;

    private String email;

    private String phonenum;

    private Byte ismanager;

    private Byte isystem;

    private Byte status;

    private String description;

    private String remark;

    private String weixinOpenId;

    private Long tenantId;

    private String wistoreMsgOpenId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getLeaderFlag() {
        return leaderFlag;
    }

    public void setLeaderFlag(String leaderFlag) {
        this.leaderFlag = leaderFlag == null ? null : leaderFlag.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public Byte getIsmanager() {
        return ismanager;
    }

    public void setIsmanager(Byte ismanager) {
        this.ismanager = ismanager;
    }

    public Byte getIsystem() {
        return isystem;
    }

    public void setIsystem(Byte isystem) {
        this.isystem = isystem;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getWeixinOpenId() {
        return weixinOpenId;
    }

    public void setWeixinOpenId(String weixinOpenId) {
        this.weixinOpenId = weixinOpenId == null ? null : weixinOpenId.trim();
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getWistoreMsgOpenId() {
        return wistoreMsgOpenId;
    }

    public void setWistoreMsgOpenId(String wistoreMsgOpenId) {
        this.wistoreMsgOpenId = wistoreMsgOpenId == null ? null : wistoreMsgOpenId.trim();
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
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getLeaderFlag() == null ? other.getLeaderFlag() == null : this.getLeaderFlag().equals(other.getLeaderFlag()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getDepartment() == null ? other.getDepartment() == null : this.getDepartment().equals(other.getDepartment()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPhonenum() == null ? other.getPhonenum() == null : this.getPhonenum().equals(other.getPhonenum()))
            && (this.getIsmanager() == null ? other.getIsmanager() == null : this.getIsmanager().equals(other.getIsmanager()))
            && (this.getIsystem() == null ? other.getIsystem() == null : this.getIsystem().equals(other.getIsystem()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getWeixinOpenId() == null ? other.getWeixinOpenId() == null : this.getWeixinOpenId().equals(other.getWeixinOpenId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getWistoreMsgOpenId() == null ? other.getWistoreMsgOpenId() == null : this.getWistoreMsgOpenId().equals(other.getWistoreMsgOpenId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getLeaderFlag() == null) ? 0 : getLeaderFlag().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getDepartment() == null) ? 0 : getDepartment().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPhonenum() == null) ? 0 : getPhonenum().hashCode());
        result = prime * result + ((getIsmanager() == null) ? 0 : getIsmanager().hashCode());
        result = prime * result + ((getIsystem() == null) ? 0 : getIsystem().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getWeixinOpenId() == null) ? 0 : getWeixinOpenId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getWistoreMsgOpenId() == null) ? 0 : getWistoreMsgOpenId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", loginName=").append(loginName);
        sb.append(", password=").append(password);
        sb.append(", leaderFlag=").append(leaderFlag);
        sb.append(", position=").append(position);
        sb.append(", department=").append(department);
        sb.append(", email=").append(email);
        sb.append(", phonenum=").append(phonenum);
        sb.append(", ismanager=").append(ismanager);
        sb.append(", isystem=").append(isystem);
        sb.append(", status=").append(status);
        sb.append(", description=").append(description);
        sb.append(", remark=").append(remark);
        sb.append(", weixinOpenId=").append(weixinOpenId);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", wistoreMsgOpenId=").append(wistoreMsgOpenId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}