package com.nowcoder.community1.dao;


import org.springframework.stereotype.Repository;

@Repository("alphaHibernate") // 访问数据库的bean应该加这个注解 () 括号类定义Bean的名字

public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select() {
        return "Hibernate";
    }
}
