package com.hopu.phone_admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hopu.phone_admin.entity.ModuleTreeNode;
import com.hopu.phone_admin.entity.TbModule;

import java.util.List;

/**
 * @Author WYS
 * @Date 2020/5/8 - 10:44
 */
public interface IModuleService extends IService<TbModule> {

    //通过用户名查询用户的两级权限
    List<ModuleTreeNode> getModuleTreeNodeByAccount(String account);

    //通过角色名称来查询角色模块菜单
    List<ModuleTreeNode> getModuleTreeNodeByRoleName(String roleName);

    //授权
    void grantModuleToRole(String moduleName,String roleId);

    //删除权限
    void removeModuleFromRole(String moduleName,String roleId);
}
