package com.lgy.xiaoyou_manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.lgy.tools.entity.TbSpec;
import com.lgy.xiaoyou_manage.service.ITbSpecService;
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
@RequestMapping("/spec")
public class TbSpecController {

    @Autowired
    ITbSpecService tbSpecService;

    @RequestMapping("/getSpecById")
    @ResponseBody
    public List<TbSpec> getSpecById(String departId){
        System.out.println(departId);
        QueryWrapper<TbSpec> wrapper = new QueryWrapper<>();
        wrapper.eq("depart_id",departId);
        List<TbSpec> specList = tbSpecService.list(wrapper);
        return specList;
    }

}
