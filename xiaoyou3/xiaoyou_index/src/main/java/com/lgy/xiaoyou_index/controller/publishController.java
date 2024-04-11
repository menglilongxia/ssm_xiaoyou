package com.lgy.xiaoyou_index.controller;



import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_index.cache.TagCache;
import com.lgy.xiaoyou_index.dto.TagDto;
import com.lgy.xiaoyou_index.entity.TbQuestion;
import com.lgy.xiaoyou_index.mapper.TbQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

//问题发布
@Controller
public class publishController {


    @Autowired
    private TbQuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(Model model) {
        //标签组
        TagCache tagCache=new TagCache();
        List<TagDto> tags = tagCache.gettags();
        model.addAttribute("tags",tags);
        return "bbs/publish";
    }

    //发布问题
    @RequestMapping("/publish")
    public String publishquestion(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam(value = "id",defaultValue = "-1")int id,
            HttpSession session,
            Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        //标签组
        TagCache tagCache=new TagCache();
        List<TagDto> tags = tagCache.gettags();
        model.addAttribute("tags",tags);
        //防止输入的问题为空
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "bbs/publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "描述不能为空");
            return "bbs/publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "bbs/publish";
        }
        //获取当前登陆用户的信息
        TbStu stu= (TbStu) session.getAttribute("tbStu");

        //将问题上传到数据库
        TbQuestion question = new TbQuestion();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreateid(stu.getUserId());
        question.setCreatetime(LocalDateTime.now());
        if(id==-1){
            questionMapper.insert(question);
        }else {
            question.setId(id);
            questionMapper.updateById(question);
        }
        return "redirect:/index";
    }

    @RequestMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")int id,
                       Model model){
        TbQuestion question=questionMapper.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        //用来标识问题是修改而不是重新创建
        model.addAttribute("id",question.getId());
        return "bbs/publish";
    }
}
