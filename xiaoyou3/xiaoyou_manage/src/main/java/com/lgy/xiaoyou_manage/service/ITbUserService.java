package com.lgy.xiaoyou_manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lgy.tools.entity.TbUser;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lgy
 * @since 2020-02-07
 */
public interface ITbUserService extends IService<TbUser> {

    TbUser selectByUserName(String username);
}
