package com.lgy.xiaoyou_manage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbImg;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lgy
 * @since 2020-03-07
 */
public interface ITbImgService extends IService<TbImg> {

    IPage<TbImg> getAllImg(long page, long limit, QueryWrapper<QueryObj> queryWrapper);

    void changeStatusById(Integer imgId, Integer imgState);
}
