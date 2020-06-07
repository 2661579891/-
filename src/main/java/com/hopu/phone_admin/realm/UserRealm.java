package com.hopu.phone_admin.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hopu.phone_admin.dao.IModuleMapper;
import com.hopu.phone_admin.dao.IRoleMapper;
import com.hopu.phone_admin.dao.IUserMapper;
import com.hopu.phone_admin.entity.TbModule;
import com.hopu.phone_admin.entity.TbRole;
import com.hopu.phone_admin.entity.TbUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//实现用户验证授权的Realm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private IRoleMapper roleMapper;

    @Autowired
    private IModuleMapper moduleMapper;

    //身份授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获得登录成功的账号
        String userName = principalCollection.getPrimaryPrincipal().toString();
        //创建授权信息对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //通过账号查询角色，添加角色
        List<TbRole> roleList = roleMapper.selectRolesByAccount(userName);
        roleList.forEach(role -> simpleAuthorizationInfo.addRole(role.getRoleName()));
        //通过账号查询权限，添加权限
        List<TbModule> moduleList = moduleMapper.selectModuleByAccount(userName);
        moduleList.forEach(module -> simpleAuthorizationInfo.addStringPermission(module.getModuleName()));
        //返回授权信息
        return simpleAuthorizationInfo;
    }

    //身份验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获得账号密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        String password = new String(token.getPassword());
        //通过账户名查询用户
        TbUser user = userMapper.selectOne(new QueryWrapper<TbUser>().eq("user_login_account", userName));
        //如果用户名不存在就抛出异常
        if (user == null) {
            throw new UnknownAccountException("不存在的用户");
        }
        //返回验证信息   1)用户名  2)数据库中正确密码  3)盐  4)获得realm名字
        return new SimpleAuthenticationInfo(
                userName,
                user.getUserLoginPwd(),
                ByteSource.Util.bytes(user.getUserLoginAccount()),
                getName());
    }
}
