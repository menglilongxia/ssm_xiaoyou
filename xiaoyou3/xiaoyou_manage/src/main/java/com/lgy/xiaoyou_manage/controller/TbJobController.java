package com.lgy.xiaoyou_manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbJob;
import com.lgy.xiaoyou_manage.service.ITbJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgy
 * @since 2020-02-17
 */
@Controller
@RequestMapping("/job")
public class TbJobController {

    @Autowired
    private ITbJobService jobService;

    /**
     * 分页查询所有的内推职位
     * @param m
     * @param page
     * @param limit
     * @param queryObj
     * @return
     */
    @RequestMapping("/getAllJob")
    public String getAllJob(Model m, @RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit, QueryObj queryObj){
        QueryWrapper<QueryObj> wrapper = new QueryWrapper<>();
        wrapper.setEntity(queryObj);
        IPage<TbJob> jobPage = jobService.getAllJob(page,limit,wrapper);

        m.addAttribute("jobPage",jobPage);
        m.addAttribute("page",page);
        m.addAttribute("queryObj",queryObj);
        System.out.println(jobPage.getRecords());
        return "/job/job-base";
    }

    @RequestMapping("/getJobById")
    public String getJobById(Model m,Integer jobId){
        TbJob job=jobService.getJobById(jobId);
        m.addAttribute("job",job);
        return "/job/job-edit";
    }

    @RequestMapping("/updateJobById")
    public String updateJobById(TbJob job){
        jobService.updateById(job);
        return "redirect:/job/getAllJob";
    }

    @RequestMapping("/delJobById")
    public String delJobById(@RequestParam("jobId") List<Integer> jobIds){
        jobService.removeByIds(jobIds);
        return "redirect:/job/getAllJob";
    }



}
