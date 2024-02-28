package com.nowcoder.community1.dao;

import com.nowcoder.community1.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper // Mybatis 的注解标识bean
public interface UserMapper {
    User selectById (int id);
    User selectByName (String name);
    User selectByEmail (String email);
    int insertUser (User user);
    int updateStatus (int id, int status);
    int updateHeader (int id, String headerUrl);
    int updatePassword (int id, String password);

}
