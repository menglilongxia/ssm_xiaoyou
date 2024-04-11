package com.lgy.xiaoyou_index.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbJob;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lgy
 * @since 2020-02-17
 */
public interface ITbJobService extends IService<TbJob> {

    IPage<TbJob> getAllJob(long page, long limit, QueryWrapper<QueryObj> queryWrapper);

    TbJob getJobById(Integer jobId);
}
