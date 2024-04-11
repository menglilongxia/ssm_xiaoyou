package com.lgy.xiaoyou_index.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_index.mapper.TbStuMapper;
import com.lgy.xiaoyou_index.service.ITbStuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lgy
 * @since 2019-12-29
 */
@Service
public class TbStuServiceImpl extends ServiceImpl<TbStuMapper, TbStu> implements ITbStuService {

    @Autowired
    TbStuMapper stuMapper;

    /**
     * 分页查询
     * @param page 第几页
     * @param limit 获取几条数据
     * @return
     */
    @Override
    public IPage<TbStu> getAllStu(long page, long limit, Wrapper<QueryObj> wrapper){
        IPage<TbStu> stuPage = stuMapper.selectStuPage(new Page<>(page,limit),wrapper);
        return stuPage;
    }

    @Override
    public TbStu getByUserName(@Param("username") String username) {
        TbStu stu=stuMapper.getByUserName(username);
        return stu;
    }

    @Override
    public IPage<TbStu> getActJoinById(Page<TbStu> page, Integer acId) {
        IPage<TbStu> stuPage = stuMapper.selectActJoinById(page,acId);
        return stuPage;
    }

    @Override
    public TbStu getStuById(Integer userId) {
        return stuMapper.getStuById(userId);
    }

    @Override
    public TbStu getMyById(Integer userId) {
        return stuMapper.getStuById(userId);
    }

}
