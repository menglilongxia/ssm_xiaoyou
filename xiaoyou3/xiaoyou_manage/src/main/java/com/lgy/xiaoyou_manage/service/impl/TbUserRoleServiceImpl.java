package com.lgy.xiaoyou_manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lgy.tools.entity.TbUserRole;
import com.lgy.xiaoyou_manage.mapper.TbUserRoleMapper;
import com.lgy.xiaoyou_manage.service.ITbUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lgy
 * @since 2020-02-07
 */
@Service
public class TbUserRoleServiceImpl extends ServiceImpl<TbUserRoleMapper, TbUserRole> implements ITbUserRoleService {

    @Autowired
    private TbUserRoleMapper userRoleMapper;

    @Override
    public List<TbUserRole> listByUserId(Integer userId) {

        return userRoleMapper.listByUserId(userId);
    }
}
