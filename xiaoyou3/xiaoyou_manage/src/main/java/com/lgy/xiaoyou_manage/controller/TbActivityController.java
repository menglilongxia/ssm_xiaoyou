package com.lgy.xiaoyou_manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.tools.common.utils.MailUtils;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbActivity;
import com.lgy.tools.entity.TbActivityJoin;
import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_manage.service.ITbActivityJoinService;
import com.lgy.xiaoyou_manage.service.ITbActivityService;
import com.lgy.xiaoyou_manage.service.ITbStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgy
 * @since 2020-02-11
 */
@Controller
@RequestMapping("/act")
public class TbActivityController {

    @Autowired
    private ITbActivityService activityService;

    @Autowired
    private ITbActivityJoinService activityJoinService;

    @Autowired
    private ITbStuService tbStuService;

    /**
     * 分页获取所有活动
     * @param m
     * @param page
     * @param limit
     * @param queryObj
     * @return
     */
    @RequestMapping("/getAllAct")
    public String getAllAct(Model m, @RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit, QueryObj queryObj){
        QueryWrapper<QueryObj> wrapper = new QueryWrapper<>();
        wrapper.setEntity(queryObj);
        IPage<TbActivity> actPage = activityService.getAllAct(page,limit,wrapper);
        m.addAttribute("actPage",actPage);
        m.addAttribute("page",page);
        m.addAttribute("queryObj",queryObj);
        return "/activity/act-base";

    }

    /**
     * 根据id获取活动信息
     * @param m
     * @param acId
     * @return
     */
    @RequestMapping("/getActById")
    public String getActById(Model m,Integer acId){
        TbActivity act=activityService.getActById(acId);
        m.addAttribute("act",act);
        return "/activity/act-edit";
    }

    /**
     * 根据Id更新活动
     * @param tbActivity
     * @return
     */
    @RequestMapping("/updateActById")
    @ResponseBody
    public Integer updateActById(TbActivity tbActivity){
        activityService.updateById(tbActivity);
        TbActivityJoin activityJoin = new TbActivityJoin();
        activityJoin.setUserId(tbActivity.getAcPer());
        activityJoin.setAcId(tbActivity.getAcId());
        boolean b = activityJoinService.save(activityJoin);
        if(b){
            TbStu tbStu = tbStuService.getOne(new QueryWrapper<TbStu>().select("name", "email").eq("user_id", tbActivity.getAcPer()));
            if(tbActivity.getAcStatus()==1&&tbStu.getEmail()!=null){
                try {
                    MailUtils.sendMail(tbStu.getEmail(),"你申请的校友活动审核通过啦"," ");
                    return 1;
                } catch (MessagingException e) {
                    e.printStackTrace();
                    return 2;
                }
            }
            if(tbActivity.getAcStatus()==2&&tbStu.getEmail()!=null){
                try {
                    MailUtils.sendMail(tbStu.getEmail(),"你申请的校友活动未通过审核","请重新申请活动");
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
     * 查询参与活动的校友
     * @param m
     * @param acId
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getActJoinById")
    public String getActJoinById(Model m,Integer acId,@RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit){
        IPage<TbStu> stuPage = tbStuService.getActJoinById(new Page<TbStu>(page,limit),acId);
        m.addAttribute("stuPage",stuPage);
        m.addAttribute("page",page);
        m.addAttribute("acId",acId);
        return "/activity/actStu-look";
    }

    /**
     * 删除加入活动的校友
     * @param userIds
     * @param acId
     * @return
     */
    @RequestMapping("/delActJoinById")
    public String delActJoinById(@RequestParam("userId") List<Integer> userIds,Integer acId){
        QueryWrapper<TbActivityJoin> wrapper = new QueryWrapper<>();
        wrapper.in("user_id",userIds);
        activityJoinService.remove(wrapper);
        return "redirect:/act/getActJoinById?acId="+acId;
    }


    /**
     * 根据id删除
     * @param acIds
     * @return
     */
    @RequestMapping("/delActById")
    public String delActById(@RequestParam("acId") List<Integer> acIds){
        activityService.removeByIds(acIds);
        QueryWrapper<TbActivityJoin> wrapper = new QueryWrapper<>();
        wrapper.in("ac_id",acIds);
        activityJoinService.remove(wrapper);
        return "redirect:/act/getAllAct";
    }

}
