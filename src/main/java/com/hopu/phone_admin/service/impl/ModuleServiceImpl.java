package com.hopu.phone_admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hopu.phone_admin.dao.IModuleMapper;
import com.hopu.phone_admin.dao.IRoleModuleMapper;
import com.hopu.phone_admin.entity.ModuleTreeNode;
import com.hopu.phone_admin.entity.TbModule;
import com.hopu.phone_admin.entity.TbRoleModule;
import com.hopu.phone_admin.service.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author WYS
 * @Date 2020/5/8 - 10:45
 */
@Service
public class ModuleServiceImpl extends ServiceImpl<IModuleMapper, TbModule> implements IModuleService {
    @Autowired
    private IModuleMapper moduleMapper;
    @Autowired
    private IRoleModuleMapper roleModuleMapper;


    @Override
    public List<ModuleTreeNode> getModuleTreeNodeByAccount(String account) {
        return moduleMapper.selectModuleTreeNodeByAccount(account);
    }

    @Override
    public List<ModuleTreeNode> getModuleTreeNodeByRoleName(String roleName) {
        return moduleMapper.selectModuleTreeNodeByRoleName(roleName);
    }

    @Override
    public void grantModuleToRole(String moduleName, String roleId) {
        //添加的如果是子权限则父级权限也要加上
        TbModule module = moduleMapper.selectOne(new QueryWrapper<TbModule>().eq("module_name", moduleName));
        if (module == null)
        {
            throw new RuntimeException("该权限名称不存在");
        }
        //判断子权限是否在中间表中
        Integer count = roleModuleMapper.selectCount(new QueryWrapper<TbRoleModule>()
                .eq("module_id", module.getModuleCode())
                .eq("role_id", roleId)
        );
        if (count>0)
        {
            throw new RuntimeException("该角色已添加该权限");
        }
        //添加权限
        TbRoleModule roleModule = new TbRoleModule(0, module.getModuleCode(), roleId);
        roleModuleMapper.insert(roleModule);
        String parentModule = module.getParentModule();
        if (!parentModule.equals("01") && !parentModule.equals("0101")){
            //判断父级权限是否在中间表中
            count = roleModuleMapper.selectCount(new QueryWrapper<TbRoleModule>()
                    .eq("module_id", parentModule)
                    .eq("role_id", roleId)
            );
            //不存在就添加父权限
            if (count==0)
            {
                roleModuleMapper.insert(new TbRoleModule(0,parentModule,roleId));
            }
        }
    }

    @Override
    public void removeModuleFromRole(String moduleName, String roleId) {
        //删除的如果是父权限则子权限全部去除
        TbModule module = moduleMapper.selectOne(new QueryWrapper<TbModule>().eq("module_name", moduleName));
        if (module == null)
        {
            throw new RuntimeException("该权限名称不存在");
        }
        //删除父子权限
        roleModuleMapper.delete(new QueryWrapper<TbRoleModule>().like("module_id",module.getModuleCode()+"%"));
    }
}
