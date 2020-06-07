package com.hopu.phone_admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hopu.phone_admin.entity.ModuleTreeNode;
import com.hopu.phone_admin.entity.TbModule;
import org.springframework.stereotype.Component;

import java.util.List;

//模块（权限控制）接口
@Component(value = "IModuleMapper")
public interface IModuleMapper extends BaseMapper<TbModule> {
    //根据用户名查询用户拥有的所有权限
    List<TbModule> selectModuleByAccount(String account);

    //通过用户名查询用户的两级权限
    List<ModuleTreeNode> selectModuleTreeNodeByAccount(String account);

    //通过角色名称来查询角色模块菜单
    List<ModuleTreeNode> selectModuleTreeNodeByRoleName(String roleName);
}
