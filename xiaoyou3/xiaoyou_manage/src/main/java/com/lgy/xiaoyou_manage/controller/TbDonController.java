package com.lgy.xiaoyou_manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbDon;
import com.lgy.xiaoyou_manage.service.ITbDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgy
 * @since 2020-03-19
 */
@Controller
@RequestMapping("/don")
public class TbDonController {

    @Autowired
    ITbDonService tbDonService;

    /**
     * 分页获取所有的捐赠记录
     * @param m
     * @param page
     * @param limit
     * @param queryObj
     * @return
     */
    @RequestMapping("/getAllDon")
    public String getAllDon(Model m, @RequestParam(defaultValue = "1") long page, @RequestParam(defaultValue = "10") long limit, QueryObj queryObj){
        QueryWrapper<QueryObj> wrapper = new QueryWrapper<>();
        wrapper.setEntity(queryObj);
        IPage<TbDon> DonPage = tbDonService.getAllDon(page,limit,wrapper);

        m.addAttribute("DonPage",DonPage);
        m.addAttribute("page",page);
        m.addAttribute("queryObj",queryObj);

        return "/don/don-base";
    }

    /**
     * 根据id查看捐赠的详细信息
     * @param donId
     * @param m
     * @return
     */
    @RequestMapping("/getDonById")
    public String getDonById(Integer donId,Model m){
        TbDon tbDon = tbDonService.getDonById(donId);
        m.addAttribute("tbDon",tbDon);
        return "/don/don-edit";
    }

    @RequestMapping("/updateDonById")
    public String updateDonById(TbDon tbDon){
        tbDonService.updateById(tbDon);
        return "redirect:/don/getAllDon";
    }

    @RequestMapping("/delDonById")
    public String delDonById(@RequestParam("donId") List<Integer> donIds){
        tbDonService.removeByIds(donIds);
        return "redirect:/don/getAllDon";
    }

}
