package com.lgy.xiaoyou_index;

import com.lgy.tools.entity.TbStu;
import com.lgy.xiaoyou_index.mapper.TbStuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XiaoyouIndexApplicationTests {
    @Autowired
    private TbStuMapper tbStuMapper;
    @Test
    void contextLoads() {
        TbStu tbStu = tbStuMapper.getStuById(1);
        System.out.println(tbStu);
    }
}
