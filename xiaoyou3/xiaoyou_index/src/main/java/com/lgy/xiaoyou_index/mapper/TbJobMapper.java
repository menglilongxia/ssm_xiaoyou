package com.lgy.xiaoyou_index.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbJob;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
public interface TbJobMapper extends BaseMapper<TbJob> {

    IPage<TbJob> getAllJob(Page page, @Param("we") QueryWrapper<QueryObj> queryWrapper);

    TbJob getJobById(Integer jobId);
}
