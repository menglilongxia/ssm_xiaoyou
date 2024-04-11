package com.lgy.xiaoyou_manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbDon;
import com.lgy.tools.entity.TbDonMoney;
import com.lgy.tools.entity.TbMonStu;
import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_manage.service.ITbDonMoneyService;
import com.lgy.xiaoyou_manage.service.ITbMonStuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgy
 * @since 2020-04-09
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

        return "/don_money/money-base";
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
     * 获取当前项目的所有捐款人
     * @param monId
     * @param m
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getMonStuById")
    public String getMonStuById(Integer monId,Model m,@RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit){
        IPage<TbMonStu> monStuPage = monStuService.getMonBymonId(page,limit,monId);
        m.addAttribute("monStuPage",monStuPage);
        m.addAttribute("page",page);
        m.addAttribute("monId",monId);
        return "/don_money/monStu-look";
    }

    /**
     * 查看捐款人捐款信息
     * @param moneyId
     * @param m
     * @return
     */
    @RequestMapping("/getMonStuMessById")
    public String getMonStuMessById(Integer moneyId,Model m){
        TbMonStu monStu = monStuService.getMonStuMessById(moneyId);
        m.addAttribute("monStu",monStu);
        return "/don_money/monStu-edit";
    }

    /**
     * 审核捐款信息
     * @param moneyId
     * @param monStatus
     * @return
     */
    @RequestMapping("/updateMonStuById")
    @ResponseBody
    public Integer updateMonStuById(Integer moneyId,Integer monStatus){
        TbMonStu monStu = monStuService.getMonStuMessById(moneyId);
        TbDonMoney donMoney = tbDonMoneyService.getMonById(monStu.getMonId());
        int m1=donMoney.getMonHad();
        int m2=monStu.getMoneyCount();
        int moneyCount =m1+m2;
        boolean b = monStuService.update(new UpdateWrapper<TbMonStu>().eq("money_id", moneyId).set("mon_status", monStatus));
        boolean b1 = tbDonMoneyService.update(new UpdateWrapper<TbDonMoney>().set("mon_had", moneyCount).eq("mon_id", monStu.getMonId()));
        if(b&&b1){
            return 1;
        }else {
            return 2;
        }
    }

    @RequestMapping("/delMonStuById")
    public String delMonStuById(@RequestParam("moneyId")List<Integer> moneyIds,Integer monId){
        System.out.println(moneyIds);
        monStuService.removeByIds(moneyIds);
        return "redirect:/mon/getMonStuById?monId="+monId;
    }



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
        return "/don_money/money-edit";
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
