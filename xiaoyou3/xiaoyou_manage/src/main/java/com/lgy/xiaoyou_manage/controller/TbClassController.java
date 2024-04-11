package com.lgy.xiaoyou_manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.lgy.tools.entity.TbClass;
import com.lgy.xiaoyou_manage.service.ITbClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgy
 * @since 2019-12-29
 */
@Controller
@RequestMapping("/cla")
public class TbClassController {

    @Autowired
    ITbClassService tbClassService;

    @RequestMapping("/getClaById")
    @ResponseBody
    public List<TbClass> getClaById(Integer specId){
        QueryWrapper<TbClass> wrapper = new QueryWrapper<>();
        wrapper.eq("spec_id",specId);
        List<TbClass> tbClasses = tbClassService.list(wrapper);
        return tbClasses;
    }

}
