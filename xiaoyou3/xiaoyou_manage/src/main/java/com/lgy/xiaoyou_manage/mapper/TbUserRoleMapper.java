package com.lgy.xiaoyou_manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.lgy.tools.entity.TbUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lgy
 * @since 2020-02-07
 */
public interface TbUserRoleMapper extends BaseMapper<TbUserRole> {

    List<TbUserRole> listByUserId(@Param("userId") Integer userId);
}
