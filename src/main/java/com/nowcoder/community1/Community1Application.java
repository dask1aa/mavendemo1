package com.nowcoder.community1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 就是一个配置文件 , 标识这个一个配置类 通常在程序入口用这个注解
public class Community1Application {

    public static void main(String[] args) {
        SpringApplication.run(Community1Application.class, args); // spring应用启动了, 底层启用了tomcat 自动创建了spring容器
    }

}
