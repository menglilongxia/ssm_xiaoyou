package com.lgy.xiaoyou_index.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbAss;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
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

    /**
     * 查询最新的三个校友会
     * @return
     */
    List<TbAss> getIndexAssList();
}
