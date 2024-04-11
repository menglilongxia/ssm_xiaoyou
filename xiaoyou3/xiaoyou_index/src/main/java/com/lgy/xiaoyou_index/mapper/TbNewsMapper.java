package com.lgy.xiaoyou_index.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbNews;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lgy
 * @since 2020-02-14
 */
public interface TbNewsMapper extends BaseMapper<TbNews> {

    IPage<TbNews> getAllNews(Page<TbNews> page, @Param("we") QueryWrapper<QueryObj> wrapper);

    TbNews getNewsById(Integer newsId);

    List<TbNews> getIndexNewsList();
}
