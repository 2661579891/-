package com.hopu.phone_admin.controller;

import com.hopu.phone_admin.entity.JsonResult;
import com.hopu.phone_admin.entity.ModuleTreeNode;
import com.hopu.phone_admin.service.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author WYS
 * @Date 2020/5/8 - 10:50
 */
@RestController
@RequestMapping("module")
public class ModuleController {
    @Autowired
    private IModuleService moduleService;

    @GetMapping("getModuleTreeNodes")
    public JsonResult getModuleTreeNodes(String account){
        try{
            List<ModuleTreeNode> nodes = moduleService.getModuleTreeNodeByAccount(account);
            return new JsonResult(200,"success",nodes);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(500,"filed",null);
        }
    }

    @GetMapping("getRoleTreeNodes")
    public JsonResult getRoleTreeNodes(String roleName){
        try{
            List<ModuleTreeNode> nodes = moduleService.getModuleTreeNodeByRoleName(roleName);
            for (ModuleTreeNode node:nodes)
            {
                boolean isChecked=false;
                //判断子节点是否选中
                for(ModuleTreeNode childNode:node.getNodes())
                {
                    if (childNode.isChecked())
                    {
                        childNode.getState().setChecked(true);
                        isChecked=true;
                    }
                }
                //子节点选中，父节点也被选中
                if(isChecked)
                {
                    node.getState().setChecked(true);
                }
            }
            return new JsonResult(200,"success",nodes);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(500,"filed",null);
        }
    }

    //授权
    @PostMapping("grantModuleToRole")
    public JsonResult grantModuleToRole(String moduleName,String roleId){
        try{
            moduleService.grantModuleToRole(moduleName,roleId);
            return new JsonResult(200,"success",null);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(500,"filed",null);
        }
    }
    //删除权限
    @PostMapping("removeModuleToRole")
    public JsonResult removeModuleToRole(String moduleName,String roleId){
        try{
            moduleService.removeModuleFromRole(moduleName,roleId);
            return new JsonResult(200,"success",null);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(500,"filed",null);
        }
    }
}
