package com.lgy.xiaoyou_manage.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbAss;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lgy
 * @since 2020-02-03
 */
public interface TbAssMapper extends BaseMapper<TbAss> {

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    IPage<TbAss> selectAssPage(Page page, @Param("we") Wrapper<QueryObj> wrapper);

    /**
     * 根据ID查询
     * @param assId
     * @return
     */
    TbAss getAssById(Integer assId);
}
