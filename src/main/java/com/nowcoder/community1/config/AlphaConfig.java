package com.nowcoder.community1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

// 对于普通的配置类
@Configuration
public class AlphaConfig {

    @Bean
    public SimpleDateFormat simpleDateFormat() { // 注意方法名就是Bean的名字
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    } // 这个方法返回的对象将被装配到容器里面 这个Bean的名字就是方法名

}
