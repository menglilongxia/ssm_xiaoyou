package com.lgy.xiaoyou_index.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbNews;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lgy
 * @since 2020-02-14
 */
public interface ITbNewsService extends IService<TbNews> {

    IPage<TbNews> getAllNews(long page, long limit, QueryWrapper<QueryObj> wrapper);

    TbNews getNewsById(Integer newsId);

    List<TbNews> getIndexNewsList();
}
