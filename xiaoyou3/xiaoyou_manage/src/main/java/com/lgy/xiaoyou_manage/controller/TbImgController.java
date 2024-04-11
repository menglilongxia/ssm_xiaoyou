package com.lgy.xiaoyou_manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgy.tools.common.utils.AliyunOSSUtil;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbImg;
import com.lgy.xiaoyou_manage.service.ITbImgService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgy
 * @since 2020-03-07
 */
@Controller
@RequestMapping("/img")
public class TbImgController {

    @Autowired
    ITbImgService tbImgService;

    /**
     * 分页查询图片
     * @param m
     * @param page
     * @param limit
     * @param queryObj
     * @return
     */
    @RequestMapping("/getAllImg")
    public String getAllImg(Model m, @RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit, QueryObj queryObj){

        QueryWrapper<QueryObj> wrapper = new QueryWrapper<>();
        wrapper.setEntity(queryObj);
        IPage<TbImg> imgPage = tbImgService.getAllImg(page,limit,wrapper);
        m.addAttribute("page",page);
        m.addAttribute("queryObj",queryObj);
        m.addAttribute("imgPage",imgPage);
        return "/settings/img";

    }

    /**
     * 是否启用轮播图
     * @param imgId
     * @param imgState
     * @return
     */
    @RequestMapping("/changeStatusById")
    public String changeStatusById(@Param("imgId") Integer imgId,@Param("imgState") Integer imgState){
        if(imgState==0){
            tbImgService.changeStatusById(imgId,1);
        }else  {
            tbImgService.changeStatusById(imgId,0);
        }

        return "redirect:/img/getAllImg";
    }

    /**
     * 上传图片
     * @param img
     * @param imgFile
     * @return
     */
    @RequestMapping("/addImg")
    public String addImg(TbImg img, MultipartFile imgFile){
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
                    img.setImgUrl(uploadUrl);
                }
                else {
                    img.setImgUrl("https://www.xync.edu.cn/images/weixintupian_20240326155421.png");
                }
            }
            img.setImgState(0);
            img.setImgTime(LocalDateTime.now());
            tbImgService.save(img);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/img/getAllImg";
    }

    @RequestMapping("/delImgById")
    public String delImgById(@RequestParam("imgId") List<Integer> imgIds){
        tbImgService.removeByIds(imgIds);
        return "redirect:/img/getAllImg";
    }



}