package com.lgy.xiaoyou_index.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbActivity;
import com.lgy.xiaoyou_index.mapper.TbActivityMapper;
import com.lgy.xiaoyou_index.service.ITbActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lgy
 * @since 2020-02-11
 */
@Service
public class TbActivityServiceImpl extends ServiceImpl<TbActivityMapper, TbActivity> implements ITbActivityService {

    @Autowired
    private TbActivityMapper activityMapper;

    @Override
    public IPage<TbActivity> getAllAct(long page, long limit, QueryWrapper<QueryObj> wrapper) {
        IPage<TbActivity> activityPage = activityMapper.selectActPage(new Page<TbActivity>(page,limit),wrapper);
        return activityPage;
    }

    @Override
    public TbActivity getActById(Integer acId) {
        TbActivity activity=activityMapper.getActById(acId);
        return activity;
    }

    @Override
    public List<TbActivity> getIndexActivityList() {

        return activityMapper.getIndexActivityList();
    }
}
