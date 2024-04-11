package com.lgy.xiaoyou_index.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbAss;
import com.lgy.tools.entity.TbStu;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lgy
 * @since 2020-02-03
 */
public interface ITbAssService extends IService<TbAss> {

    /**
     * 分页查询校友会信息
     * @param page
     * @param limit
     * @return
     */
    IPage<TbAss> getAllAss(long page, long limit, QueryWrapper<QueryObj> queryWrapper);

    /**
     * 根据ID查询校友会
     * @param assId
     * @return
     */
    TbAss getAssById(Integer assId);

    /**
     * 根据校友会ID查询所有的校友
     * @param assId
     * @return
     */
    IPage<TbStu> getAssStuById(Page page, Integer assId);

    List<TbAss> getIndexAssList();
}
