package com.hopu.phone_admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hopu.phone_admin.entity.TbRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "IRoleMapper")
public interface IRoleMapper extends BaseMapper<TbRole> {
    //根据账号查询用户所有角色
    List<TbRole> selectRolesByAccount(String account);
}
