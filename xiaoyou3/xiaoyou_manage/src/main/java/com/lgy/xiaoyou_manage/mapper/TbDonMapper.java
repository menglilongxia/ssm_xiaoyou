package com.lgy.xiaoyou_manage.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbDon;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lgy
 * @since 2020-03-19
 */
public interface TbDonMapper extends BaseMapper<TbDon> {

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    IPage<TbDon> getAllDon(Page<TbDon> page,@Param("we") QueryWrapper<QueryObj> wrapper);

    TbDon getDonById(Integer donId);
}
