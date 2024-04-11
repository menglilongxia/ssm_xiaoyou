package com.lgy.xiaoyou_index.controller;


import com.lgy.xiaoyou_index.enums.NotificationEnum;
import com.lgy.xiaoyou_index.mapper.TbCommentMapper;
import com.lgy.xiaoyou_index.mapper.TbNotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//将通知设置为已读，并且跳转到问题页面
@Controller
public class NotificationController {

    @Autowired
    private TbNotificationMapper notificationMapper;
    @Autowired
    private TbCommentMapper commentMapper;

    @RequestMapping("/notification/{action}")
    public String notification(@PathVariable("action")int id){
        //将通知设置为已读
        notificationMapper.updateStatus(id);
        //获取type，检验是回复评论还是回复问题
        int type=notificationMapper.getTypeByid(id);
        int outerid=notificationMapper.getOuterIdById(id);
        int questionid;
        if(type== NotificationEnum.NOTIFICATION_QUESTION.getType()){
            questionid=outerid;
        }else {
            questionid=commentMapper.getParentIdById(outerid);
        }
        return "redirect:/question/"+questionid;
    }
}
