package com.hopu.phone_admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hopu.phone_admin.dao.IUserMapper;
import com.hopu.phone_admin.entity.TbUser;
import com.hopu.phone_admin.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, TbUser> implements IUserService {
}
