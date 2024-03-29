package com.nowcoder.community1.controller;

import com.nowcoder.community1.entity.DiscussPost;
import com.nowcoder.community1.entity.Page;
import com.nowcoder.community1.entity.User;
import com.nowcoder.community1.service.DiscussPostService;
import com.nowcoder.community1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    // 这里响应的是HTML页面,不用谢@ResponseBody
    public String getIndexPage (Model model, Page page) {
        // 方法调用前,SpringMVC会自动实例化Model和Page,并将Page注入Model
        // 所以,在thymeleaf中可以直接访问Page对象中的数据
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        // 分页查询存入list
        List<DiscussPost> list = discussPostService.findDiscussPosts(0,page.getOffset(),page.getLimit());
        // 封装DiscussPost对象, 使得帖子和用户对应
        List<Map<String, Object>> discussPosts = new ArrayList<>();

        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "/index";
    }
}
