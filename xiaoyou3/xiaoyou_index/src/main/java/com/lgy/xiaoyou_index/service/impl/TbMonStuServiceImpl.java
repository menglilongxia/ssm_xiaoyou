package com.lgy.xiaoyou_index.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgy.tools.entity.TbMonStu;
import com.lgy.xiaoyou_index.mapper.TbMonStuMapper;
import com.lgy.xiaoyou_index.service.ITbMonStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lgy
 * @since 2020-04-10
 */
@Service
public class TbMonStuServiceImpl extends ServiceImpl<TbMonStuMapper, TbMonStu> implements ITbMonStuService {

    @Autowired
    TbMonStuMapper monStuMapper;

    @Override
    public IPage<TbMonStu> getMonBymonId(long page, long limit, Integer monId) {
        return monStuMapper.getMonBymonId(new Page<TbMonStu>(page,limit),monId);
    }

    @Override
    public IPage<TbMonStu> getAllMonStu(long page, long limit) {
        return monStuMapper.getAllMonStu(new Page<TbMonStu>(page,limit));
    }

    @Override
    public TbMonStu sumMoney() {
        return monStuMapper.sumMoney();
    }
}
