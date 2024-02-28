package com.nowcoder.community1.service;

import com.nowcoder.community1.dao.AlphaDao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service // 扫描业务组件要加这个注解
//@Scope("prototype") // Bean作用范围 默认单例 ，多例用prototype
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public AlphaService () {
        System.out.println("实例化AlphaService"); // 加个构造器
    }
    @PostConstruct // 这个方法会在构造器之后调用
    public void init() {
        System.out.println("初始化AlphaService");
    }

    @PreDestroy // 在销毁对象之前调用这个方法
    public void destroy () {
        System.out.println("销毁AlphaService");
    }

    public String find() {
        return alphaDao.select();
    }
}
