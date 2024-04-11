package com.lgy.xiaoyou_index.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbStu;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lgy
 * @since 2019-12-29
 */
public interface ITbStuService extends IService<TbStu> {


    /**
     * 条件分页查询
     * @param page
     * @param limit
     * @param wrapper
     * @return
     */
    IPage<TbStu> getAllStu(long page, long limit, Wrapper<QueryObj> wrapper);

    TbStu getByUserName(String username);

    IPage<TbStu> getActJoinById(Page<TbStu> page, Integer acId);

    TbStu getStuById(Integer userId);

    TbStu getMyById(Integer userId);
}
