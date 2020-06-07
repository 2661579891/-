package com.hopu.phone_admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tb_user")
public class TbUser {
  @TableId(type = IdType.INPUT)
  private String userId;
  private String userName;
  private String userLoginAccount;
  private String userLoginPwd;
  private String remark;
  private String userType;
  private String enabled;
  private java.sql.Timestamp loginTime;
  private String roleId;
  private String userIcon;

  @Override
  public String toString() {
    return "TbUser{" +
            "userId='" + userId + '\'' +
            ", userName='" + userName + '\'' +
            ", userLoginAccount='" + userLoginAccount + '\'' +
            ", userLoginPwd='" + userLoginPwd + '\'' +
            ", remark='" + remark + '\'' +
            ", userType='" + userType + '\'' +
            ", enabled='" + enabled + '\'' +
            ", loginTime=" + loginTime +
            ", roleId='" + roleId + '\'' +
            ", userIcon='" + userIcon + '\'' +
            '}';
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserLoginAccount() {
    return userLoginAccount;
  }

  public void setUserLoginAccount(String userLoginAccount) {
    this.userLoginAccount = userLoginAccount;
  }


  public String getUserLoginPwd() {
    return userLoginPwd;
  }

  public void setUserLoginPwd(String userLoginPwd) {
    this.userLoginPwd = userLoginPwd;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }


  public String getEnabled() {
    return enabled;
  }

  public void setEnabled(String enabled) {
    this.enabled = enabled;
  }


  public java.sql.Timestamp getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(java.sql.Timestamp loginTime) {
    this.loginTime = loginTime;
  }


  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }


  public String getUserIcon() {
    return userIcon;
  }

  public void setUserIcon(String userIcon) {
    this.userIcon = userIcon;
  }

}
