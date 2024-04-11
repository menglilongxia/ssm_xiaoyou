package com.lgy.xiaoyou_index.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbJob;
import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_index.service.ITbJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
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
        return "/job/job-list";
    }

    /**
     * 根据id获得岗位
     * @param m
     * @param jobId
     * @return
     */
    @RequestMapping("/getJobById")
    public String getJobById(Model m,Integer jobId){
        TbJob job=jobService.getJobById(jobId);
        m.addAttribute("job",job);
        return "/job/job-look";
    }

    /**
     * 跳转到添加岗位页面
     * @return
     */
    @RequestMapping("/addNewJob")
    public String addNewJob(){
        return "/job/job-add";
    }

    /**
     * 保存岗位
     * @param job
     * @return
     */
    @RequestMapping("/addJob")
    public String addJob(TbJob job, HttpSession session){
        TbStu stu= (TbStu) session.getAttribute("tbStu");
        job.setUserId(stu.getUserId());
        job.setJobTime(LocalDateTime.now());
        job.setCheckStatus(0);
        jobService.save(job);
        return "redirect:/job/getAllJob";
    }



}
