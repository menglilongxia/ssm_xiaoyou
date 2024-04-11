package com.lgy.xiaoyou_index.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbAss;
import com.lgy.tools.entity.TbAssstu;
import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_index.service.ITbAssService;
import com.lgy.xiaoyou_index.service.ITbAssstuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *

 */
@Controller
@RequestMapping("/ass")
public class TbAssController {

    @Autowired
    ITbAssService tbAssService;

    @Autowired
    ITbAssstuService tbAssstuService;

    /**
     * 分页查询校友会信息
     * @param m
     * @param page
     * @param limit
     * @param queryObj
     * @return
     */
    @RequestMapping("/getAllAss")
    public String getAllAss(Model m, @RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit, QueryObj queryObj){
        QueryWrapper<QueryObj> wrapper = new QueryWrapper<>();
        wrapper.setEntity(queryObj);
        IPage<TbAss> assPage = tbAssService.getAllAss(page,limit,wrapper);
        m.addAttribute("assPage",assPage);
        m.addAttribute("page",page);
        m.addAttribute("queryObj",queryObj);
        return "/ass/ass-list";

    }

    /**
     * 根据ID查询
     * @param m
     * @param assId
     * @return
     */
    @RequestMapping("/getAssById")
    public String getAssById(Model m,Integer assId){
        TbAss tbAss = tbAssService.getAssById(assId);
        m.addAttribute("ass",tbAss);
        return "/ass/ass-look";
    }

    /**
     * 跳转到创建校友会的界面
     * @return
     */
    @RequestMapping("/createAss")
    public String createAss(){
        return "/ass/ass-add";
    }

    /**
     * 创建校友会
     * @param tbAss
     * @return
     */
    @RequestMapping("/addAss")
    @ResponseBody
    public Integer addAss(TbAss tbAss,HttpSession session){
        TbStu tbStu= (TbStu) session.getAttribute("tbStu");
        //设置创建人
        tbAss.setAssPer(tbStu.getUserId());
        //设置审核状态
        tbAss.setAssStatus(0);
        //设置创建时间
        tbAss.setAssCreatetime(LocalDateTime.now());
        List<TbAss> list = tbAssService.list(new QueryWrapper<TbAss>().eq("ass_name", tbAss.getAssName()).select("ass_name"));
        if(list!=null){
            for (TbAss ass : list) {
                if(ass.getAssName().equals(tbAss.getAssName())){
                    return 2;
                }
            }
        }
        boolean b = tbAssService.save(tbAss);
        if(b){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 加入校友会
     * @param assId
     * @param session
     * @return
     */
    @RequestMapping("/addStuToAss")
    @ResponseBody
    public Integer addStuToAss(Integer assId, HttpSession session){
        TbStu tbStu= (TbStu) session.getAttribute("tbStu");
        TbAssstu assstu = new TbAssstu();
        assstu.setAssId(assId);
        assstu.setUserId(tbStu.getUserId());
        List<TbAssstu> assstuList = tbAssstuService.list(new QueryWrapper<TbAssstu>().eq("user_id", tbStu.getUserId()));
        for (TbAssstu tbAssstu : assstuList) {
            if (tbAssstu.getAssId().equals(assId)){
                System.out.println("-----已经加入了-----");
                return 2;
            }
        }
        boolean b = tbAssstuService.save(assstu);
        if(b){
            return 1;
        }else {
            return 0;
        }
    }




    /**
     * 根据校友会ID查询所有的校友
     * @param m
     * @param assId
     * @return
     */
    @RequestMapping("/getAssStuById")
    public String getAssStuById(Model m,Integer assId,@RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit){
        IPage<TbStu> stuList=tbAssService.getAssStuById(new Page<>(page,limit),assId);
        m.addAttribute("stuPage",stuList);
        m.addAttribute("page",page);
        m.addAttribute("assId",assId);
        return "/aluAss/AssStu-look";
    }



}
