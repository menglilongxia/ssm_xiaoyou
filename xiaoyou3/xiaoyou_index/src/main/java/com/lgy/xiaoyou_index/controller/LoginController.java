package com.lgy.xiaoyou_index.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgy.tools.entity.*;
import com.lgy.xiaoyou_index.entity.TbQuestion;
import com.lgy.xiaoyou_index.mapper.TbNotificationMapper;
import com.lgy.xiaoyou_index.service.*;
import com.lgy.xiaoyou_index.service.impl.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@Controller
public class LoginController {


    @Autowired
    private QuestionService questionService;
    @Autowired
    private TbNotificationMapper notificationMapper;
    @Autowired
    private ITbNewsService tbNewsService;
    @Autowired
    private ITbAssService tbAssService;
    @Autowired
    private ITbActivityService tbActivityService;
    @Autowired
    private ITbImgService tbImgService;
    @Autowired
    private ITbStuService tbStuService;

    @Autowired
    private ITbMonStuService monStuService;

    @RequestMapping("/bbs")
    public String bbs( Model model,
                        @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {

        IPage<TbQuestion> questionIPage = questionService.list(page, limit);
        model.addAttribute("questionIPage", questionIPage);
        //获取阅读量最高的十篇问题
        List<TbQuestion> tbQuestions = questionService.gettopten();
        model.addAttribute("tbQuestions", tbQuestions);
        model.addAttribute("page",page);
        return "bbs/bbs";
    }

    @RequestMapping("/index")
    public String index(HttpSession session,Model m){
        TbStu stu= (TbStu) session.getAttribute("tbStu");
        if(stu!=null){
            //获取未读的消息数量
            int unreadnum = notificationMapper.getUnReadCount(stu.getUserId());
            session.setAttribute("unreadnum", unreadnum);
        }
        else {
            session.setAttribute("unreadnum", "0");
        }
        List<TbNews> tbNewsList= tbNewsService.getIndexNewsList();
        m.addAttribute("tbNewsList",tbNewsList);
        List<TbAss> tbAssList = tbAssService.getIndexAssList();
        m.addAttribute("tbAssList",tbAssList);
        List<TbActivity> activityList = tbActivityService.getIndexActivityList();
        m.addAttribute("activityList",activityList);
        List<TbImg> imgList = tbImgService.getIndexImgList();
        TbImg img = imgList.get(0);
        imgList.remove(0);
        TbMonStu monStu = monStuService.sumMoney();
        m.addAttribute("sumMoney",monStu.getSumMoney());
        m.addAttribute("img",img);
        m.addAttribute("imgList",imgList);
        return "index";
    }



    @RequestMapping("/login")
    public String showLogin() {

        return "login";
    }

    @RequestMapping("/check/UserName")
    @ResponseBody
    public Integer checkUserName(@Param("username") String username){
        TbStu tbStu = tbStuService.getOne(new QueryWrapper<TbStu>().eq("username", username).select("username"));
        if(tbStu!=null){
            return 1;
        }else {
            return 2;
        }
    }

    @RequestMapping("/check/PassWord")
    @ResponseBody
    public Integer checkPassWord(@Param("password") String password,@Param("username") String username){
        TbStu tbStu = tbStuService.getOne(new QueryWrapper<TbStu>().eq("password", password).eq("username",username).select("username"));
        if(tbStu!=null){
            return 1;
        }else {
            return 2;
        }
    }

    @RequestMapping("/register")
    public String register(){

        return "/register";
    }

    @RequestMapping("/password")
    public String pwd(){
        return "/my/my-password";
    }

    @RequestMapping("/editPwd")
    @ResponseBody
    public Integer editPwd(@Param("newpwd") String newpwd, HttpSession session){
        System.out.println(newpwd);
        TbStu tbStu= (TbStu) session.getAttribute("tbStu");
        boolean b = tbStuService.update(new UpdateWrapper<TbStu>().set("password", newpwd).eq("user_id", tbStu.getUserId()));
        if(b){
            return 1;
        }else {
            return 0;
        }
    }





}
