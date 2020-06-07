package com.hopu.phone_admin;

import com.hopu.phone_admin.dao.IModuleMapper;
import com.hopu.phone_admin.dao.IUserMapper;
import com.hopu.phone_admin.entity.ModuleTreeNode;
import com.hopu.phone_admin.entity.TbRole;
import com.hopu.phone_admin.entity.TbUser;
import com.hopu.phone_admin.service.IRoleService;
import com.hopu.phone_admin.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneAdminApplicationTests {

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Test
    public void testSelect() {
        List<TbUser> users=userMapper.selectList(null);
        users.forEach((user)-> System.out.println(user));
        System.out.println();
        List<TbUser> users1=userService.list();
        users1.forEach((user)-> System.out.println(user));
    }

    @Autowired
    private IModuleMapper moduleMapper;
    @Test
    public void testTreeView(){
        List<ModuleTreeNode> treeNodes = moduleMapper.selectModuleTreeNodeByAccount("admin");
        treeNodes.forEach(tree-> System.out.println(tree));
    }

    @Test
    public void testAddRole(){

        boolean result1=roleService.saveOrUpdate(new TbRole("111111","测试","测试用例",1,2));
        System.out.println(result1);
    }

    @Test
    public void testCheckId(){
        TbRole role = roleService.getById("11111");
        System.out.println(role);
    }
}
