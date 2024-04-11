package com.lgy.xiaoyou_index.controller;


import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_index.entity.TbComment;
import com.lgy.xiaoyou_index.entity.TbQuestion;
import com.lgy.xiaoyou_index.mapper.TbNotificationMapper;
import com.lgy.xiaoyou_index.service.impl.CommentService;
import com.lgy.xiaoyou_index.service.impl.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

//问题详情
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;
    @Autowired
    private TbNotificationMapper notificationMapper;

    @RequestMapping("/question/{id}")
    public String question(@PathVariable(name = "id")int id,
                           Model model,
                           HttpSession session){

        TbStu stu= (TbStu) session.getAttribute("tbStu");
        //获取未读的消息数量
        int unreadnum = notificationMapper.getUnReadCount(stu.getUserId());
        session.setAttribute("unreadnum", unreadnum);
        TbQuestion tbQuestion = questionService.getbyid(id);
        //增加阅读数
        questionService.increaseview(id);
        model.addAttribute("tbQuestion",tbQuestion);
        //展示回复数据
        List<TbComment> tbComments = commentService.getByid(id);
        model.addAttribute("tbComments",tbComments);
        //相关问题
        String[] tags=tbQuestion.getTag().split(",");
        StringBuilder msg=new StringBuilder();
        for (String tag:tags){
            msg.append(tag);
            msg.append("|");
        }
        String result=msg.substring(0,msg.length()-1);
        List<TbQuestion> relativeQuestion = questionService.getbytag(id, result);
        model.addAttribute("relativeQuestion",relativeQuestion);

        return "bbs/question";
    }
}
