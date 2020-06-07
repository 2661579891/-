package com.hopu.phone_admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tb_role")
public class TbRole {
  @TableId(type = IdType.INPUT)
  private String roleId;
  private String roleName;
  private String roleDesc;
  private long roleOrder;
  private long roleType;


  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public String getRoleDesc() {
    return roleDesc;
  }

  public void setRoleDesc(String roleDesc) {
    this.roleDesc = roleDesc;
  }


  public long getRoleOrder() {
    return roleOrder;
  }

  public void setRoleOrder(long roleOrder) {
    this.roleOrder = roleOrder;
  }


  public long getRoleType() {
    return roleType;
  }

  public void setRoleType(long roleType) {
    this.roleType = roleType;
  }

  public TbRole() {
  }

  public TbRole(String roleId, String roleName, String roleDesc, long roleOrder, long roleType) {
    this.roleId = roleId;
    this.roleName = roleName;
    this.roleDesc = roleDesc;
    this.roleOrder = roleOrder;
    this.roleType = roleType;
  }

  @Override
  public String toString() {
    return "TbRole{" +
            "roleId='" + roleId + '\'' +
            ", roleName='" + roleName + '\'' +
            ", roleDesc='" + roleDesc + '\'' +
            ", roleOrder=" + roleOrder +
            ", roleType=" + roleType +
            '}';
  }
}
