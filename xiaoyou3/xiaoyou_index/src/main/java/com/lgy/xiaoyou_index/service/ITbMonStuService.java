package com.lgy.xiaoyou_index.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lgy.tools.entity.TbMonStu;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lgy
 * @since 2020-04-10
 */
public interface ITbMonStuService extends IService<TbMonStu> {

    IPage<TbMonStu> getMonBymonId(long page, long limit, Integer monId);

    IPage<TbMonStu> getAllMonStu(long page, long limit);

    TbMonStu sumMoney();
}
