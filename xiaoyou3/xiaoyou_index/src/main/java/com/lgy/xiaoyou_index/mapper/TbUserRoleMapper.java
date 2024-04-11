package com.lgy.xiaoyou_index.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lgy.tools.entity.TbUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *

 */
public interface TbUserRoleMapper extends BaseMapper<TbUserRole> {

    List<TbUserRole> listByUserId(@Param("userId") Integer userId);
}
