package com.lgy.xiaoyou_index.controller;



import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_index.dto.CommentCreateDto;
import com.lgy.xiaoyou_index.dto.ResultDto;
import com.lgy.xiaoyou_index.entity.*;
import com.lgy.xiaoyou_index.enums.NotificationStatusEnum;
import com.lgy.xiaoyou_index.enums.NotificationEnum;
import com.lgy.xiaoyou_index.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

//回复功能
@Controller
public class CommentController {

    @Autowired
    private TbCommentMapper commentMapper;
    @Autowired
    private TbQuestionMapper questionMapper;
    @Autowired
    private TbNotificationMapper notificationMapper;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDto commentCreateDto,
                       HttpSession session){

        TbStu stu= (TbStu) session.getAttribute("tbStu");
                    //获取未读的消息数量
        int unreadnum=notificationMapper.getUnReadCount(stu.getUserId());
        session.setAttribute("unreadnum",unreadnum);

        //把评论插入数据库
        TbComment comment=new TbComment();
        comment.setParentId(commentCreateDto.getParent_id());
        comment.setContent(commentCreateDto.getContent());
        comment.setType(commentCreateDto.getType());
        comment.setCreatetime(LocalDateTime.now());
        comment.setCommentor(stu.getUserId());
        commentMapper.insert(comment);

        if (commentCreateDto.getType()==2){
            //把回复评论的通知插入数据库
            TbNotification notification=new TbNotification();
            notification.setNotifier(comment.getCommentor());
            TbComment comment2=commentMapper.getParentById(commentCreateDto.getParent_id());
            notification.setReceiver(comment2.getCommentor());
            notification.setOuterid(commentCreateDto.getParent_id());
            notification.setType(NotificationEnum.NOTIFICATION_COMMENT.getType());
            notification.setCreatetime(LocalDateTime.now());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notificationMapper.insertNotification(notification);

            //增加评论数
            commentMapper.updateCommentCount(commentCreateDto.getParent_id());
        }else {
            //把回复问题的通知插入数据库
            TbQuestion question=questionMapper.getById(commentCreateDto.getParent_id());
            TbNotification notification=new TbNotification();
            notification.setNotifier(stu.getUserId());
            notification.setReceiver(question.getCreateid());
            notification.setOuterid(commentCreateDto.getParent_id());
            notification.setType(NotificationEnum.NOTIFICATION_QUESTION.getType());
            notification.setCreatetime(LocalDateTime.now());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notificationMapper.insertNotification(notification);
            //增加问题回复量
            questionMapper.updateComment(commentCreateDto.getParent_id());
        }
        ResultDto resultDto=new ResultDto();
        return resultDto.success();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDto<List<TbComment>> comments(@PathVariable(name = "id") int id,
                                                HttpSession session){
        //查找type=2，即是回复评论的评论
        TbStu stu= (TbStu) session.getAttribute("tbStu");
        Integer userId = stu.getUserId();
        List<TbComment> comments = commentMapper.getCommentByid(id,userId,2);

        return new ResultDto<TbComment>().success(comments);
    }
}
