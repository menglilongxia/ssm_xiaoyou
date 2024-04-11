package com.lgy.xiaoyou_index.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbNews;
import com.lgy.xiaoyou_index.mapper.TbNewsMapper;
import com.lgy.xiaoyou_index.service.ITbNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lgy
 * @since 2020-02-14
 */
@Service
public class TbNewsServiceImpl extends ServiceImpl<TbNewsMapper, TbNews> implements ITbNewsService {

    @Autowired
    private TbNewsMapper newsMapper;

    @Override
    public IPage<TbNews> getAllNews(long page, long limit, QueryWrapper<QueryObj> wrapper) {
        return  newsMapper.getAllNews(new Page<>(page, limit),wrapper);
    }

    @Override
    public TbNews getNewsById(Integer newsId) {

        return newsMapper.getNewsById(newsId);
    }

    @Override
    public List<TbNews> getIndexNewsList() {
        List<TbNews> newsList = newsMapper.getIndexNewsList();
        return newsList;
    }
}
