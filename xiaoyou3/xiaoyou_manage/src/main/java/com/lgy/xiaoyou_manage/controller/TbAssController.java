package com.lgy.xiaoyou_manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.lgy.tools.common.utils.MailUtils;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbAss;
import com.lgy.tools.entity.TbAssstu;
import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_manage.service.ITbAssService;
import com.lgy.xiaoyou_manage.service.ITbAssstuService;
import com.lgy.xiaoyou_manage.service.ITbStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgy
 * @since 2020-02-03
 */
@Controller
@RequestMapping("/ass")
public class TbAssController {

    @Autowired
    ITbAssService tbAssService;

    @Autowired
    ITbAssstuService tbAssstuService;

    @Autowired
    ITbStuService tbStuService;

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
        return "/aluAss/aluAss-base";

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
        return "/aluAss/aluAss-edit";
    }

    /**
     * 更新信息
     * @param tbAss
     * @return
     */

    @RequestMapping("/updateAssById")
    @ResponseBody
    public Integer updateAssById(TbAss tbAss)  {
        tbAss.setAssExaminetime(LocalDateTime.now());
        tbAssService.updateById(tbAss);
        TbAssstu assstu = new TbAssstu();
        assstu.setUserId(tbAss.getAssPer());
        assstu.setAssId(tbAss.getAssId());
        boolean b = tbAssstuService.save(assstu);
        if(b){
            TbStu tbStu = tbStuService.getOne(new QueryWrapper<TbStu>().select("name", "email").eq("user_id", tbAss.getAssPer()));
            if(tbAss.getAssStatus()==1&&tbStu.getEmail()!=null){
                try {
                    MailUtils.sendMail(tbStu.getEmail(),"你申请的校友会审核通过啦"," ");
                    return 1;
                } catch (MessagingException e) {
                    e.printStackTrace();
                    return 2;
                }
            }
            if(tbAss.getAssStatus()==2&&tbStu.getEmail()!=null){
                try {
                    MailUtils.sendMail(tbStu.getEmail(),"你申请的校友会未通过审核","请重新申请");
                    return 1;
                } catch (MessagingException e) {
                    e.printStackTrace();
                    return 2;
                }
            }
        }
        return 2;
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

    /**
     * 删除校友会
     * @param assIds
     * @return
     */
    @RequestMapping("delAssById")
    public String delAssById(@RequestParam("assId")List<Integer> assIds){
        tbAssService.removeByIds(assIds);
        QueryWrapper<TbAssstu> wrapper = new QueryWrapper<>();
        wrapper.in("ass_id",assIds);
        tbAssstuService.remove(wrapper);
        return "redirect:/ass/getAllAss";
    }

    /**
     * 根据校友ID删除对应的校友会成员信息表
     * @param userIds
     * @return
     */
    @RequestMapping("/delAssStuById")
    public String delAssStuById(@RequestParam("userId") List<Integer> userIds,Integer assId){
        QueryWrapper<TbAssstu> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id",userIds);
        tbAssstuService.remove(queryWrapper);
        return "redirect:/ass/getAssStuById?assId="+assId;
    }

}
