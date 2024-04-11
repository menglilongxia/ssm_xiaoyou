package com.lgy.xiaoyou_index.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbJob;
import com.lgy.xiaoyou_index.mapper.TbJobMapper;
import com.lgy.xiaoyou_index.service.ITbJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lgy
 * @since 2020-02-17
 */
@Service
public class TbJobServiceImpl extends ServiceImpl<TbJobMapper, TbJob> implements ITbJobService {

    @Autowired
    private TbJobMapper jobMapper;

    @Override
    public IPage<TbJob> getAllJob(long page, long limit, QueryWrapper<QueryObj> queryWrapper) {

        return jobMapper.getAllJob(new Page<>(page,limit),queryWrapper);
    }

    @Override
    public TbJob getJobById(Integer jobId) {
        return jobMapper.getJobById(jobId);
    }
}
