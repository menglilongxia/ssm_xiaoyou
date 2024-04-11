package com.lgy.xiaoyou_index.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgy.tools.common.utils.QueryObj;
import com.lgy.tools.entity.TbDon;
import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_index.service.ITbDonService;
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
        IPage<TbDon> donPage = tbDonService.getAllDon(page,limit,wrapper);
        m.addAttribute("donPage",donPage);
        m.addAttribute("page",page);
        m.addAttribute("queryObj",queryObj);

        return "/don/don-list";
    }

    /**
     * 跳转到捐赠页面
     * @return
     */
    @RequestMapping("/createNewDon")
    public String createNewDon(){
        return "/don/don-add";
    }

    @RequestMapping("/addDon")
    @ResponseBody
    public Integer addDon(TbDon tbDon, HttpSession session){
        TbStu tbStu= (TbStu) session.getAttribute("tbStu");
        if(tbDon.getDonName()!=null&& !"".equals(tbDon.getDonName().trim())&&tbDon.getDonDesc()!=null&&!"".equals(tbDon.getDonDesc())){
            tbDon.setDonPer(tbStu.getUserId());
            tbDon.setDonStatus(0);
            tbDon.setDonTime(LocalDateTime.now());
            boolean b = tbDonService.save(tbDon);
            if (b) {
                return 1;
            } else {
                return 0;
            }
        }else {
            return 0;
        }
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
        return "/don/don-look";
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
