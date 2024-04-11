package com.lgy.xiaoyou_index.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_index.entity.TbNotification;
import com.lgy.xiaoyou_index.entity.TbQuestion;
import com.lgy.xiaoyou_index.mapper.TbNotificationMapper;
import com.lgy.xiaoyou_index.service.impl.NotificationService;
import com.lgy.xiaoyou_index.service.impl.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

//个人中心
@Controller
public class PersonalController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private TbNotificationMapper notificationMapper;
    @RequestMapping("/personal")
    public String personal(@RequestParam("action") String action,
                           Model model,
                           HttpSession session,
                           @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit){
        TbStu stu= (TbStu) session.getAttribute("tbStu");
        //获取未读的消息数量
        int unreadnum = notificationMapper.getUnReadCount(stu.getUserId());
        session.setAttribute("unreadnum", unreadnum);
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionname","我的问题");
            IPage<TbQuestion> questionIPage = questionService.list(stu.getUserId(), page, limit);
            model.addAttribute("questionIPage", questionIPage);
        }else if ("information".equals(action)){
            model.addAttribute("section","information");
            model.addAttribute("sectionname","我的消息");
            IPage<TbNotification> NotificationPage = notificationService.list(stu.getUserId(), page, limit);
            model.addAttribute("NotificationPage",NotificationPage);
        }
        model.addAttribute("page",page);

        return "bbs/personal";
    }
}
