package com.nowcoder.community1.controller;

import com.nowcoder.community1.entity.User;
import com.nowcoder.community1.service.UserService;
import com.nowcoder.community1.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class LoginController implements CommunityConstant {

    @Autowired
    UserService userService;


    //这个注解用于映射HTTP的GET请求到getRegisterPage方法。当用户访问/register路径时，会返回注册页面的路径。
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {
        return "/site/register";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "/site/login";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST) // 这里用POST请求是因为这个请求是浏览器向我们提交数据
    public String register(Model model, User user) { // 这里 SpringMVC 会自动把user存入model里
        Map<String, Object> map = userService.register(user);

        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "注册成功，请尽快到你的邮箱激活");
            model.addAttribute("target", "/index");
            return "/site/operate-result";
        } else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));

            return "/site/register";
        }
    }

    @RequestMapping(path = "/activation/{userId}/{code}", method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId") int userId, @PathVariable("code") String code) {
        int result = userService.activation(userId, code);
        if (result == ACTIVATION_SUCCESS) {
            model.addAttribute("msg", "激活成功，账号已经可以使用了");
            model.addAttribute("target", "/login");
        } else if (result == ACTIVATION_REPEAT) {
            model.addAttribute("msg", "无效操作，账号已经被激活过");
            model.addAttribute("target", "/index");
        } else {
            model.addAttribute("msg", "激活失败，激活码错误");
            model.addAttribute("target", "/index");
        }
        return "/site/operate-result";
    }
}