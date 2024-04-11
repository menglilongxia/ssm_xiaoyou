package com.lgy.xiaoyou_manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgy.tools.common.utils.AliyunOSSUtil;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbStu;
import com.lgy.tools.entity.TbUserRole;
import com.lgy.xiaoyou_manage.service.ITbStuService;
import com.lgy.xiaoyou_manage.service.ITbUserRoleService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgy
 * @since 2019-12-29
 */
@Controller
@RequestMapping("/stu")
public class TbStuController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ITbStuService stuService;

    @Autowired
    ITbUserRoleService userRoleService;


    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getAllStu")
    public String getAllStu(Model m, @RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit, QueryObj queryObj){
        QueryWrapper<QueryObj> wrapper = new QueryWrapper<>();
        wrapper.setEntity(queryObj);
        IPage<TbStu> stu = stuService.getAllStu(page,limit,wrapper);
        m.addAttribute("stuPage",stu);
        m.addAttribute("page",page);
        m.addAttribute("queryObj",queryObj);
        return "/stu/stu-base";
    }

    /**
     * 添加校友信息
     * @param
     * @return
     */
    @RequestMapping("/addStu")
    public String addStu(){
        return "/stu/stu-add";
    }

    /**
     * 保存校友信息
     * @param m
     * @param tbStu
     * @return
     */
    @RequestMapping("/SaveStu")
    public String SaveStu(Model m, TbStu tbStu, MultipartFile file){
        logger.info("文件上传");
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        try {
            if (!"".equals(filename.trim())) {
                File newFile = new File(filename);
                FileOutputStream os = new FileOutputStream(newFile);
                os.write(file.getBytes());
                os.close();
                file.transferTo(newFile);
                // 上传到OSS
                String uploadUrl = AliyunOSSUtil.upLoad(newFile);
                newFile.delete();
                tbStu.setStuImg(uploadUrl);
            }
            else {
                tbStu.setStuImg("https://alumnis.oss-rg-china-mainland.aliyuncs.com/xiaoyou/2024stu.jpg");
            }
            stuService.save(tbStu);
            Integer userId = tbStu.getUserId();
            TbUserRole userRole = new TbUserRole();
            userRole.setUserId(userId);
            if(userId==1){
                userRole.setRoleId(1);
            }else {
                userRole.setRoleId(2);
            }
            userRoleService.save(userRole);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/stu/getAllStu";
    }

    /**
     * 多选删除
     * @param userIds
     * @return
     */
    @RequestMapping("/delStu")
    public String delStu(@RequestParam("userId")List<Integer> userIds){
        stuService.removeByIds(userIds);
        return "redirect:/stu/getAllStu";
    }

    /**
     * 根据Id删除
     * @param userId
     * @return
     */
    @RequestMapping("/delStuById")
    public String delStuById(Integer userId){
        stuService.removeById(userId);
        return "redirect:/stu/getAllStu";
    }



}
