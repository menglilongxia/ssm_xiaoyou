package com.lgy.xiaoyou_index.service.impl;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.xiaoyou_index.entity.TbNotification;
import com.lgy.xiaoyou_index.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author l9216
 */
@Service
public class NotificationService {

    @Autowired
    private TbNotificationMapper notificationMapper;

    public IPage<TbNotification> list(int userId, int page, int size) {
        return notificationMapper.getNotificationPage(new Page<>(page,size),userId);
    }
}
