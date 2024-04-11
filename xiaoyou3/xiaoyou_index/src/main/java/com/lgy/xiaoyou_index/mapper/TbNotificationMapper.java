package com.lgy.xiaoyou_index.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.xiaoyou_index.entity.TbNotification;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lgy
 * @since 2020-02-18
 */
public interface TbNotificationMapper extends BaseMapper<TbNotification> {


    void updateStatus(Integer id);

    Integer getTypeByid(Integer id);

    Integer getOuterIdById(Integer id);

    IPage<TbNotification> getNotificationPage(Page page, @Param("userId")Integer userId);

    Integer getUnReadCount(@Param("id") Integer id);

    void insertNotification(TbNotification notification);
}
