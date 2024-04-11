package com.lgy.xiaoyou_index.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbDonMoney;
import com.lgy.xiaoyou_index.mapper.TbDonMoneyMapper;
import com.lgy.xiaoyou_index.service.ITbDonMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lgy
 * @since 2020-04-09
 */
@Service
public class TbDonMoneyServiceImpl extends ServiceImpl<TbDonMoneyMapper, TbDonMoney> implements ITbDonMoneyService {

    @Autowired
    private TbDonMoneyMapper donMoneyMapper;
    @Override
    public IPage<TbDonMoney> getAllMoney(long page, long limit, QueryWrapper<QueryObj> wrapper) {
        return donMoneyMapper.getAllMoney(new Page<>(page,limit),wrapper);
    }

    @Override
    public TbDonMoney getMonById(Integer monId) {
        return donMoneyMapper.getMonById(monId);
    }
}
