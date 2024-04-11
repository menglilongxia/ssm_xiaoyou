package com.lgy.xiaoyou_index.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbDonMoney;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
public interface TbDonMoneyMapper extends BaseMapper<TbDonMoney> {

    /**
     * 分页查询
     * @param Page
     * @param wrapper
     * @return
     */
    IPage<TbDonMoney> getAllMoney(Page Page, @Param("we") QueryWrapper<QueryObj> wrapper);

    TbDonMoney getMonById(Integer monId);
}
