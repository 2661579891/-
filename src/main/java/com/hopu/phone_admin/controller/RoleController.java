package com.hopu.phone_admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hopu.phone_admin.entity.JsonResult;
import com.hopu.phone_admin.entity.TbRole;
import com.hopu.phone_admin.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.relation.Role;
import java.util.List;

/**
 * @Author WYS
 * @Date 2020/5/8 - 18:10
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("role-list")
    public String listRoles(Model model){
        List<TbRole> roles = roleService.list();
        model.addAttribute("roles",roles);
        return "role-list";
    }

    /**
     * 增加角色
     */
    @RequestMapping("addRole")
    @ResponseBody
    public JsonResult addRole(TbRole role){
        boolean result = roleService.save(role);
        if(result == true)
            return new JsonResult(200,"success","添加成功");
        return new JsonResult(500,"filed","添加失败");
    }

    /**
     * 更新角色
     */
    @RequestMapping(value = "updateRole",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateRole(TbRole role){
       boolean result = roleService.saveOrUpdate(role);
        if(result == true)
            return new JsonResult(200,"success","更新成功");
        return new JsonResult(500,"filed","更新失败");
    }

    /**
     * 删除角色
     */
    @RequestMapping(value = "deleteRole",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult deleteRole(String roleId){
        boolean result = roleService.removeById(roleId);
        if (result)
            return new JsonResult(200,"success","删除成功");
        return new JsonResult(500,"filed","删除失败");
    }

    /**
     * 检查roleId是否重复
     */
    @RequestMapping(value = "checkRoleId",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult checkRoleId(String roleId){
        TbRole role = roleService.getById(roleId);
        if(role == null)
            return new JsonResult(200,"true","可以使用");
        return new JsonResult(500,"false","已经存在");
    }
}
