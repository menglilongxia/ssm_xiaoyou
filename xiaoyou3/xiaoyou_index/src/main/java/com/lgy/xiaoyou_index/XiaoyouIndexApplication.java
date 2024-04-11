package com.lgy.xiaoyou_index;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.lgy.xiaoyou_index.mapper")
public class XiaoyouIndexApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoyouIndexApplication.class, args);
    }

}
