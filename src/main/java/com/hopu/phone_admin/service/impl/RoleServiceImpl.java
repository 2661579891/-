package com.hopu.phone_admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hopu.phone_admin.dao.IRoleMapper;
import com.hopu.phone_admin.entity.TbRole;
import com.hopu.phone_admin.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * @Author WYS
 * @Date 2020/5/8 - 18:12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<IRoleMapper, TbRole> implements IRoleService {
}
