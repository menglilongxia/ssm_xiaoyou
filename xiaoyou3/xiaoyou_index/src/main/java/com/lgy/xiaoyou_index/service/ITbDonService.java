package com.lgy.xiaoyou_index.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbDon;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lgy
 * @since 2020-03-19
 */
public interface ITbDonService extends IService<TbDon> {

    IPage<TbDon> getAllDon(long page, long limit, QueryWrapper<QueryObj> wrapper);

    TbDon getDonById(Integer donId);
}
