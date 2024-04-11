package com.lgy.xiaoyou_index.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbDon;
import com.lgy.xiaoyou_index.mapper.TbDonMapper;
import com.lgy.xiaoyou_index.service.ITbDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lgy
 * @since 2020-03-19
 */
@Service
public class TbDonServiceImpl extends ServiceImpl<TbDonMapper, TbDon> implements ITbDonService {

    @Autowired
    private TbDonMapper tbDonMapper;

    @Override
    public IPage<TbDon> getAllDon(long page, long limit, QueryWrapper<QueryObj> wrapper) {
        return tbDonMapper.getAllDon(new Page<TbDon>(page,limit),wrapper);
    }

    @Override
    public TbDon getDonById(Integer donId) {
        return tbDonMapper.getDonById(donId);
    }
}
