package com.hopu.phone_admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tb_role_module")
public class TbRoleModule {
  @TableId(type = IdType.INPUT)
  private long id;
  private String moduleId;
  private String roleId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getModuleId() {
    return moduleId;
  }

  public void setModuleId(String moduleId) {
    this.moduleId = moduleId;
  }


  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public TbRoleModule()
  {

  }

  public TbRoleModule(long id, String moduleId, String roleId) {
    this.id = id;
    this.moduleId = moduleId;
    this.roleId = roleId;
  }
}
