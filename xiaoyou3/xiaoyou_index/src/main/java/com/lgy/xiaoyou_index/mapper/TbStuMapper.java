package com.lgy.xiaoyou_index.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.tools.common.utils.QueryObj;

import com.lgy.tools.entity.TbStu;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *

 */
public interface TbStuMapper extends BaseMapper<TbStu> {


    /**
     * 分页查询所有的校友信息
     * @param page
     * @param wrapper
     * @return
     */
    IPage<TbStu> selectStuPage(Page page, @Param("we") Wrapper<QueryObj> wrapper);

    /**
     * 分页查询校友会下的校友信息
     * @param page
     * @param assId
     * @return
     */
    IPage<TbStu> getAssStuById(Page page, Integer assId);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    TbStu getByUserName(String username);

    /**
     * 查询参与活动的校友
     * @param page
     * @param acId
     * @return
     */
    IPage<TbStu> selectActJoinById(Page page, Integer acId);

    /**
     * 根据ID获取校友信息
     *
     */
    TbStu getStuById(Integer userId);

    TbStu getMyById(Integer userId);
}
