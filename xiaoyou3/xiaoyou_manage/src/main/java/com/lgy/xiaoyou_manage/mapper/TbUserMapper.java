package com.lgy.xiaoyou_manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.lgy.tools.entity.TbUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lgy
 * @since 2020-02-07
 */
public interface TbUserMapper extends BaseMapper<TbUser> {


    TbUser selectByUserName(@Param("username") String username);
}
