package com.lgy.xiaoyou_index.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgy.tools.common.utils.AliyunOSSUtil;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.*;
import com.lgy.xiaoyou_index.service.*;
import org.slf4j.LoggerFactory;
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
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@Controller
@RequestMapping("/stu")
public class TbStuController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ITbStuService stuService;

    @Autowired
    ITbUserRoleService userRoleService;

    @Autowired
    ITbAssService assService;

    @Autowired
    ITbAssstuService assstuService;

    @Autowired
    ITbActivityService activityService;

    @Autowired
    ITbActivityJoinService activityJoinService;

    @Autowired
    ITbDonService donService;

    @Autowired
    ITbMonStuService monStuService;

    @Autowired
    ITbDonMoneyService donMoneyService;



    /**
     * 跳转到完善个人信息界面
     * @param
     * @return
     */
    @RequestMapping("/addMy")
    public String addStu(Model m, HttpSession session){
        TbStu tbStu= (TbStu) session.getAttribute("tbStu");
        TbStu stu = stuService.getMyById(tbStu.getUserId());
        m.addAttribute("stu",stu);

        return "/my/my-add";
    }

    /**
     * 保存个人信息
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
            stuService.updateById(tbStu);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/index";
    }

    /**
     * 我的主页
     * @return
     */
    @RequestMapping("/myIndex")
    public String myIndex(HttpSession session,Model m){
        TbStu tbStu= (TbStu) session.getAttribute("tbStu");
        Integer userId=tbStu.getUserId();
        List<TbAssstu> assstuList = assstuService.list(new QueryWrapper<TbAssstu>().eq("user_id", userId));
        List<Integer> assIds = new ArrayList<>();
        for (TbAssstu tbAssstu : assstuList) {
            if(tbAssstu.getAssId()!=null){
                assIds.add(tbAssstu.getAssId());
            }
        }
        if(assIds.size()!=0){
            List<TbAss> MyAssList = assService.list(new QueryWrapper<TbAss>().in("ass_id",assIds).eq("ass_status",1).select("ass_id","ass_name","ass_createtime"));
            m.addAttribute("MyAssList",MyAssList);
        }else {
            m.addAttribute("MyAssList",new ArrayList<TbAss>());
        }

        List<TbActivityJoin> activityJoinList = activityJoinService.list(new QueryWrapper<TbActivityJoin>().eq("user_id", userId));
        List<Integer> acIds=new ArrayList<>();
        for (TbActivityJoin tbActivityJoin : activityJoinList) {
            if(tbActivityJoin.getAcId()!=null){
                acIds.add(tbActivityJoin.getAcId());
            }
        }
        if(acIds.size()!=0){
            List<TbActivity> MyActivityList = activityService.list(new QueryWrapper<TbActivity>().in("ac_id",acIds).eq("ac_status",1).select("ac_id","ac_title","ac_create_time"));
            m.addAttribute("MyActivityList",MyActivityList);
        }else {
            m.addAttribute("MyActivityList",new ArrayList<TbActivity>());
        }


        List<TbMonStu> tbMonStus = monStuService.list(new QueryWrapper<TbMonStu>().eq("user_id", userId).select("mon_id"));
        List<Integer> monIds=new ArrayList<>();
        for (TbMonStu monStus : tbMonStus) {
            if(monStus.getMonId()!=null){
                monIds.add(monStus.getMonId());
            }
        }
        if(monIds.size()!=0){
            List<TbDonMoney> donMoneyList = donMoneyService.list(new QueryWrapper<TbDonMoney>().in("mon_id", monIds).select("mon_id", "mon_name", "mon_time"));
            m.addAttribute("donMoneyList",donMoneyList);
        }else {
            m.addAttribute("donMoneyList",new ArrayList<TbDonMoney>());
        }

        List<TbDon> MyDonList = donService.list(new QueryWrapper<TbDon>().eq("don_per", userId).eq("don_status",1).select("don_id","don_name","don_time"));
        m.addAttribute("MyDonList",MyDonList);
        return "/my/my-index";
    }

    /**
     * 保存注册信息
     * @param tbStu
     * @return
     */
    @RequestMapping("/SaveRegister")
    public String saveRegister(TbStu tbStu, MultipartFile file){
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
                tbStu.setStuImg("https://lgyfile.oss-cn-beijing.aliyuncs.com/xiaoyou/2020-01-29/20191230164912.jpg");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        tbStu.setIsAdmin(0);
        stuService.save(tbStu);
        TbUserRole userRole = new TbUserRole();
        userRole.setUserId(tbStu.getUserId());
        userRole.setRoleId(2);
        userRoleService.save(userRole);
        return "redirect:/login";
    }


    @RequestMapping("/checkUser")
    @ResponseBody
    public Integer  checkUser(String username){
        List<TbStu> stuList = stuService.list(new QueryWrapper<TbStu>().eq("username", username).select("username"));
        for (TbStu stu : stuList) {
            if(stu.getUsername().equals(username)){
                return 0;
            }
        }
        return 1;
    }


}
