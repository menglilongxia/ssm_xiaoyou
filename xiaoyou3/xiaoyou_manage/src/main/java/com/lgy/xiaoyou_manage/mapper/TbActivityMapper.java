package com.lgy.xiaoyou_manage.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbActivity;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lgy
 * @since 2020-02-11
 */
public interface TbActivityMapper extends BaseMapper<TbActivity> {

    IPage<TbActivity> selectActPage(Page page, @Param("we") QueryWrapper<QueryObj> wrapper);

    TbActivity getActById(Integer acId);
}
