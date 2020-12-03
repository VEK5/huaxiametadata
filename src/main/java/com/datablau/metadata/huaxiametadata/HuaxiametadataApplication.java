package com.datablau.metadata.huaxiametadata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//首先启动类把自动配置数据源关闭
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HuaxiametadataApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuaxiametadataApplication.class, args);
    }

}
