package com.lgy.xiaoyou_manage.controller;




import com.lgy.tools.entity.TbDepart;
import com.lgy.xiaoyou_manage.service.ITbDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("dep")
public class TbDepartController {

    @Autowired
    ITbDepartService tbDepartService;

    @RequestMapping("/getAllDepart")
    @ResponseBody
    public List<TbDepart> getDepart(){
        List<TbDepart> list = tbDepartService.list();
        return list;
    }

}
