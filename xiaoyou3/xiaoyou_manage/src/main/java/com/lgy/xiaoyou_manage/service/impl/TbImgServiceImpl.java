package com.lgy.xiaoyou_manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbImg;
import com.lgy.xiaoyou_manage.mapper.TbImgMapper;
import com.lgy.xiaoyou_manage.service.ITbImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lgy
 * @since 2020-03-07
 */
@Service
public class TbImgServiceImpl extends ServiceImpl<TbImgMapper, TbImg> implements ITbImgService {

    @Autowired
    private TbImgMapper imgMapper;

    @Override
    public IPage<TbImg> getAllImg(long page, long limit, QueryWrapper<QueryObj> queryWrapper) {
        return imgMapper.getAllImg(new Page<>(page,limit),queryWrapper);
    }

    @Override
    public void changeStatusById(Integer imgId, Integer imgState) {
        imgMapper.changeStatusById(imgId, imgState);
    }
}
