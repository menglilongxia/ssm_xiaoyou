package com.lgy.xiaoyou_manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lgy.tools.entity.TbUser;
import com.lgy.xiaoyou_manage.mapper.TbUserMapper;
import com.lgy.xiaoyou_manage.service.ITbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lgy
 * @since 2020-02-07
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

    @Autowired
    TbUserMapper userMapper;

    @Override
    public TbUser selectByUserName(String username) {
        TbUser user=userMapper.selectByUserName(username);
        return user;
    }
}
