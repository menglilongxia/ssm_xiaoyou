package com.lgy.xiaoyou_index.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbDonMoney;
import com.lgy.tools.entity.TbMonStu;
import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_index.service.ITbDonMoneyService;
import com.lgy.xiaoyou_index.service.ITbMonStuService;
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
 *
 */
@Controller
@RequestMapping("/mon")
public class TbDonMoneyController {

    @Autowired
    ITbDonMoneyService tbDonMoneyService;

    @Autowired
    ITbMonStuService monStuService;

    /**
     * 分页查询
     * @param m
     * @param page
     * @param limit
     * @param queryObj
     * @return
     */
    @RequestMapping("/getAllMoney")
    public String getAllMoney(Model m, @RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit, QueryObj queryObj){
        QueryWrapper<QueryObj> wrapper = new QueryWrapper<>();
        wrapper.setEntity(queryObj);
        IPage<TbDonMoney> monPage = tbDonMoneyService.getAllMoney(page,limit,wrapper);
        m.addAttribute("monPage",monPage);
        m.addAttribute("page",page);
        m.addAttribute("queryObj",queryObj);

        return "/don_money/money-list";
    }

    /**
     * 添加筹款项目
     * @return
     */
    @RequestMapping("/addMon")
    public String addMon(){

        return "/don_money/money-add";
    }

    /**
     * 获取项目的捐款人
     * @param monId
     * @param m
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getMonStuById")
    public String getMonStuById(Integer monId,Model m,@RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit){
        IPage<TbMonStu> monStuPage = monStuService.getMonBymonId(page,limit,monId);
        TbDonMoney donMoney = tbDonMoneyService.getMonById(monId);
        m.addAttribute("monStuPage",monStuPage);
        m.addAttribute("page",page);
        m.addAttribute("monId",monId);
        m.addAttribute("donMoney",donMoney);
        return "/don_money/showMonStuByMonId";
    }

    /**
     * 删除项目捐款人
     * @param moneyIds
     * @param monId
     * @return
     */
    @RequestMapping("/delMonStuById")
    public String delMonStuById(@RequestParam("moneyId")List<Integer> moneyIds,Integer monId){
        System.out.println(moneyIds);
        monStuService.removeByIds(moneyIds);
        return "redirect:/mon/getMonStuById?monId="+monId;
    }

    /**
     * 为项目捐款
     * @param monStu
     * @param session
     * @return
     */
    @RequestMapping("/createNewMonStu")
    public String createNewMonStu(TbMonStu monStu,HttpSession session){
        TbStu tbStu= (TbStu) session.getAttribute("tbStu");
        monStu.setMoneyTime(LocalDateTime.now());
        monStu.setUserId(tbStu.getUserId());
        monStu.setMonStatus(0);
        TbDonMoney donMoney = tbDonMoneyService.getMonById(monStu.getMonId());
        int m1=donMoney.getMonHad();
        int m2=monStu.getMoneyCount();
        int moneyCount =m1+m2;
        System.out.println(moneyCount+"=============================");
        monStuService.save(monStu);
        //tbDonMoneyService.update(new UpdateWrapper<TbDonMoney>().set("mon_had",moneyCount).eq("mon_id",monStu.getMonId()));
        return "redirect:/mon/getMonById?monId="+monStu.getMonId();
    }

    /**
     * 校友捐赠名录
     * @param m
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/showMonStu")
    public String showMonStu(Model m,@RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit){
        IPage<TbMonStu> monStuPage = monStuService.getAllMonStu(page,limit);
        m.addAttribute("page",page);
        m.addAttribute("monStuPage",monStuPage);
        return "/don_money/showMonStu";
    }



    /**
     * 保存筹款项目
     * @param tbDonMoney
     * @param session
     * @return
     */
    @RequestMapping("/saveMon")
    public String saveMon(TbDonMoney tbDonMoney, HttpSession session){
        TbStu tbStu= (TbStu) session.getAttribute("tbStu");
        tbDonMoney.setMonPer(tbStu.getUserId());
        tbDonMoney.setMonHad(0);
        tbDonMoney.setMonTime(LocalDateTime.now());
        tbDonMoney.setMonStatus(0);
        tbDonMoneyService.save(tbDonMoney);
        return "redirect:/mon/getAllMoney";
    }

    /**
     * 根据id查看筹款的详细信息
     * @param monId
     * @param m
     * @return
     */
    @RequestMapping("/getMonById")
    public String getDonById(Integer monId,Model m){
        TbDonMoney tbDonMoney = tbDonMoneyService.getMonById(monId);
        m.addAttribute("tbMon",tbDonMoney);
        return "/don_money/money-look";
    }

    @RequestMapping("/updateMonById")
    public String updateMonById(TbDonMoney tbDonMoney){
        tbDonMoneyService.updateById(tbDonMoney);
        return "redirect:/mon/getAllMoney";
    }

    @RequestMapping("/delMonById")
    public String delMonById(@RequestParam("monId") List<Integer> monIds){
        tbDonMoneyService.removeByIds(monIds);
        return "redirect:/mon/getAllMoney";
    }

}
