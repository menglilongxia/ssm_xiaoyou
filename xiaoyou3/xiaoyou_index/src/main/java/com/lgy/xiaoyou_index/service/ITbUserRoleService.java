package com.lgy.xiaoyou_index.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lgy.tools.entity.TbUserRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lgy
 * @since 2020-02-07
 */
public interface ITbUserRoleService extends IService<TbUserRole> {

    List<TbUserRole> listByUserId(Integer userId);
}
