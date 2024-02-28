package com.nowcoder.community1.dao;

import com.nowcoder.community1.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    // userId = 0 时是个人主页, != 0 时是查询讨论页面 , offset是每一页起始行的行号, limit指每一页最多显示多少条数据
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit); // 分页查询

    //  @Param注解用于给参数取别名
    // 如果只有一个参数, 并且在<if>里使用,则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId); // 查询帖子总数

}
