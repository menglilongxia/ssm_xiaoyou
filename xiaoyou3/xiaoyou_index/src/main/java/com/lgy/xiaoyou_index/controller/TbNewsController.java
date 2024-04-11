package com.lgy.xiaoyou_index.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbNews;
import com.lgy.tools.entity.TbStu;

import com.lgy.xiaoyou_index.service.ITbNewsService;
import com.lgy.xiaoyou_index.service.ITbStuService;
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
 */
@Controller
@RequestMapping("/news")
public class TbNewsController {


    @Autowired
    ITbNewsService newsService;

    @Autowired
    ITbStuService stuService;


    /**
     * 分页查看新闻列表
     * @param m
     * @param page
     * @param limit
     * @param queryObj
     * @return
     */
    @RequestMapping("/getAllNews")
    public String getAllNews(Model m, @RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit, QueryObj queryObj, HttpSession session){

        QueryWrapper<QueryObj> wrapper = new QueryWrapper<>();

        wrapper.setEntity(queryObj);

        IPage<TbNews> newsPage = newsService.getAllNews(page,limit,wrapper);
        m.addAttribute("newsPage",newsPage);
        m.addAttribute("page",page);
        m.addAttribute("queryObj",queryObj);
        return "/news/news-list";
    }

    /**
     * 根据新闻Id查询
     * @param m
     * @param newsId
     * @return
     */
    @RequestMapping("/getNewsById")
    public String getNewsById(Model m,Integer newsId){
        TbNews news=newsService.getNewsById(newsId);
        m.addAttribute("news",news);
        return "/news/news-look";
    }


}
