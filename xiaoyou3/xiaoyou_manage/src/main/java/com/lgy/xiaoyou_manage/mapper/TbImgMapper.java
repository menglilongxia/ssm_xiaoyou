package com.lgy.xiaoyou_manage.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbImg;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lgy
 * @since 2020-03-07
 */
public interface TbImgMapper extends BaseMapper<TbImg> {

    /**
     * 分页查询图片
     * @param page
     * @param queryObj
     * @return
     */
    IPage<TbImg> getAllImg(Page page, @Param("we") QueryWrapper<QueryObj> queryObj);

    void changeStatusById(Integer imgId, Integer imgState);
}
