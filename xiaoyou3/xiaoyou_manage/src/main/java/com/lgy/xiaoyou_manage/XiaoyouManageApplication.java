package com.lgy.xiaoyou_manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author l9216
 */
@SpringBootApplication
@MapperScan("com.lgy.xiaoyou_manage.mapper")
public class XiaoyouManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoyouManageApplication.class, args);
    }

}
