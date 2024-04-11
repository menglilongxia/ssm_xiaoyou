package com.lgy.xiaoyou_index.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbDonMoney;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lgy
 * @since 2020-04-09
 */
public interface ITbDonMoneyService extends IService<TbDonMoney> {

    IPage<TbDonMoney> getAllMoney(long page, long limit, QueryWrapper<QueryObj> wrapper);

    TbDonMoney getMonById(Integer monId);
}
