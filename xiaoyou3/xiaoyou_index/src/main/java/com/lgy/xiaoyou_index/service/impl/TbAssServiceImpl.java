package com.lgy.xiaoyou_index.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbAss;
import com.lgy.tools.entity.TbStu;

import com.lgy.xiaoyou_index.mapper.TbAssMapper;
import com.lgy.xiaoyou_index.mapper.TbStuMapper;
import com.lgy.xiaoyou_index.service.ITbAssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lgy
 * @since 2020-02-03
 */
@Service
public class TbAssServiceImpl extends ServiceImpl<TbAssMapper, TbAss> implements ITbAssService {

    @Autowired
    TbAssMapper tbAssMapper;

    @Autowired
    TbStuMapper tbStuMapper;

    @Override
    public IPage<TbAss> getAllAss(long page, long limit, QueryWrapper<QueryObj> queryWrapper) {
        IPage<TbAss> tbAssPage = tbAssMapper.selectAssPage(new Page<TbAss>(page, limit),queryWrapper);
        return tbAssPage;
    }

    @Override
    public TbAss getAssById(Integer assId) {
        TbAss tbAss=tbAssMapper.getAssById(assId);
        return tbAss;
    }

    @Override
    public IPage<TbStu> getAssStuById(Page page, Integer assId) {
        IPage<TbStu> tbStuIPage= tbStuMapper.getAssStuById(page,assId);
        return tbStuIPage;
    }

    @Override
    public List<TbAss> getIndexAssList() {
        return tbAssMapper.getIndexAssList();
    }
}
