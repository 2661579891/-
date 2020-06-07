package com.hopu.phone_admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hopu.phone_admin.entity.TbRoleModule;
import org.springframework.stereotype.Component;

/**
 * @Author WYS
 * @Date 2020/5/9 - 12:05
 */
@Component("IRoleModuleMapper")
public interface IRoleModuleMapper extends BaseMapper<TbRoleModule> {
}
