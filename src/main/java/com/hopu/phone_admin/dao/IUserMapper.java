package com.hopu.phone_admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hopu.phone_admin.entity.TbUser;
import org.springframework.stereotype.Component;

@Component(value = "IUserMapper")
public interface IUserMapper extends BaseMapper<TbUser> {
}
