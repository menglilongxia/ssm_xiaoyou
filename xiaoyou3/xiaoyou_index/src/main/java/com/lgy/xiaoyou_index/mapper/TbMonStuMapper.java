package com.lgy.xiaoyou_index.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.tools.entity.TbMonStu;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lgy
 * @since 2020-04-10
 */
public interface TbMonStuMapper extends BaseMapper<TbMonStu> {

    IPage<TbMonStu> getMonBymonId(Page page, @Param("monId") Integer monId);

    IPage<TbMonStu> getAllMonStu(Page<TbMonStu> tbMonStuPage);

    TbMonStu sumMoney();
}
