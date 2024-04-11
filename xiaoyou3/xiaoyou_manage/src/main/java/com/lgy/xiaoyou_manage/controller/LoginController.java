package com.lgy.xiaoyou_manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lgy.tools.entity.TbStu;
import com.lgy.tools.entity.TbUserRole;
import com.lgy.xiaoyou_manage.config.CustomUserDetailsService;
import com.lgy.xiaoyou_manage.service.ITbStuService;
import com.lgy.xiaoyou_manage.service.ITbUserRoleService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgy
 * @since 2020-02-07
 */
@Controller
public class LoginController {

    @Autowired
    private ITbStuService tbStuService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private ITbUserRoleService userRoleService;

    private Logger logger=  LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/home")
    public String showHome(Model m, Authentication authentication){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登录用户:"+name);
        TbStu tbStu = tbStuService.getByUserName(name);
        m.addAttribute("tbStu",tbStu);
        return "home";
    }

    @RequestMapping("/login")
    public String showLogin() {

        return "login";
    }

    @RequestMapping("/check/UserName")
    @ResponseBody
    public Integer checkUserName(@Param("username") String username){
        TbStu tbStu = tbStuService.getOne(new QueryWrapper<TbStu>().eq("username", username).select("username"));
        if(tbStu!=null){
            return 1;
        }else {
            return 2;
        }
    }

    /*@RequestMapping("/check/PassWord")
    @ResponseBody
    public Integer checkPassWord(@Param("password") String password,@Param("username") String username){
        TbStu tbStu = tbStuService.getOne(new QueryWrapper<TbStu>().eq("password", password).eq("username",username).select("username"));
        if(tbStu!=null){
            return 1;
        }else {
            return 2;
        }
    }*/

    @RequestMapping("/check/PassWord")
    @ResponseBody
    public Integer checkRole(@Param("password") String password,@Param("username") String username){
        TbStu tbStu = tbStuService.getOne(new QueryWrapper<TbStu>().eq("password", password).eq("username",username).select("username","user_id"));
        if(tbStu==null){
            return 2;
        }else {
            TbUserRole userRole = userRoleService.getById(tbStu.getUserId());
            if(userRole!=null){
                if(userRole.getRoleId()==1){
                    return 1;
                }else {
                    return 3;
                }
            }else {
                return 3;
            }
        }
    }

    @RequestMapping("/index")
    public String showIndex(){
        return "index";
    }

    @RequestMapping("/password")
    public String pwd(){
        return "/settings/password";
    }

    @RequestMapping("/editPwd")
    @ResponseBody
    public Integer editPwd(@Param("newpwd") String newpwd, HttpSession session){
        System.out.println(newpwd);
        TbStu tbStu= (TbStu) session.getAttribute("tbStu");
        boolean b = tbStuService.update(new UpdateWrapper<TbStu>().set("password", newpwd).eq("user_id", tbStu.getUserId()));
        if(b){
            return 1;
        }else {
            return 0;
        }
    }


    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }

}
