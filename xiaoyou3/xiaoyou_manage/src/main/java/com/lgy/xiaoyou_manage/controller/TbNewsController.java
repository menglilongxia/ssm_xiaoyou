package com.lgy.xiaoyou_manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgy.tools.common.utils.AliyunOSSUtil;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbNews;
import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_manage.service.ITbNewsService;
import com.lgy.xiaoyou_manage.service.ITbStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgy
 * @since 2020-02-14
 */
@Controller
@RequestMapping("/news")
public class TbNewsController {



    @Autowired
    ITbNewsService newsService;

    @Autowired
    ITbStuService stuService;


    /**
     * 跳转到发布新闻界面
     * @return
     */
    @RequestMapping("/addNews")
    public String addNews(){
        return "/news/news-add";
    }


    @RequestMapping("/uploadNewsImg")
    @ResponseBody
    public Map<String,Object> uploadNewsImg(@RequestParam("imgFile") List<MultipartFile> imgFiles){
        Map<String, Object> map = new HashMap<>();
        List<String> data = new ArrayList<>();
        for (MultipartFile imgFile : imgFiles) {
            String filename = imgFile.getOriginalFilename();
            System.out.println(filename);
            try {
                if (imgFile != null) {
                    if (!"".equals(filename.trim())) {
                        File newFile = new File(filename);
                        FileOutputStream os = new FileOutputStream(newFile);
                        os.write(imgFile.getBytes());
                        os.close();
                        imgFile.transferTo(newFile);
                        // 上传到OSS
                        String uploadUrl = AliyunOSSUtil.upLoad(newFile);
                        newFile.delete();
                        data.add(uploadUrl);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        map.put("error",0);
        map.put("data",data);
        return map;
    }


    /**
     * 添加新闻
     * @param news
     * @return
     */
    @RequestMapping("/saveNews")
    public String saveNews(TbNews news, HttpSession session){
        TbStu tbStu= (TbStu) session.getAttribute("tbStu");
        news.setNewsUser(tbStu.getUserId());
        news.setNewsTime(LocalDateTime.now());
        newsService.saveOrUpdate(news);
        return "redirect:/news/getAllMyNews";
    }

    /**
     * 查看当前登录用户的
     * @param m
     * @param page
     * @param limit
     * @param queryObj
     * @return
     */
    @RequestMapping("/getAllMyNews")
    public String getAllNews(Model m, @RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit, QueryObj queryObj, HttpSession session){

        QueryWrapper<QueryObj> wrapper = new QueryWrapper<>();
        TbStu tbStu= (TbStu) session.getAttribute("tbStu");
        queryObj.setTid((Integer) tbStu.getUserId());
        wrapper.setEntity(queryObj);

        IPage<TbNews> newsPage = newsService.getAllNews(page,limit,wrapper);
        m.addAttribute("newsPage",newsPage);
        m.addAttribute("page",page);
        m.addAttribute("queryObj",queryObj);
        return "/news/news-base";
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
        return "/news/news-edit";
    }

    /**
     * 修改更新新闻
     * @param tbNews
     * @return
     */
    @RequestMapping("/updateNews")
    public String updateNews(TbNews tbNews){
        tbNews.setNewsTime(LocalDateTime.now());
        newsService.updateById(tbNews);
        return "redirect:/news/getAllMyNews";
    }

    /**
     * 删除新闻
     * @param newsIds
     * @return
     */
    @RequestMapping("/delNewsById")
    public String delNewsById(@RequestParam("newsId") List<Integer> newsIds){
        newsService.removeByIds(newsIds);
        return "redirect:/news/getAllMyNews";
    }


}
