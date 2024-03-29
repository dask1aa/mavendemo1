package com.nowcoder.community1.controller;

import com.nowcoder.community1.service.AlphaService;
import com.nowcoder.community1.util.CommunityUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;



@Controller
@RequestMapping("/alpha") // 给这个类取一个访问的名
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello") // 给浏览器返回一个页面
    @ResponseBody
    public String sayHello(){
        return "Hello spring boot";
    }

    @RequestMapping("/data")
    @ResponseBody // 返回界面
    public String getData() {
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http (HttpServletRequest request, HttpServletResponse response) {
        // 获取请求数据
        System.out.println(request.getMethod()); // 获取请求方式
        System.out.println(request.getServletPath()); // 获取路径
        Enumeration<String> enumeration = request.getHeaderNames(); // 头
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + "=" + value);
        }
        System.out.println(request.getParameter("code"));

        response.setContentType("text/html; charset=UTF-8");
        try (
                PrintWriter writer = response.getWriter();
        ){
            writer.write("<h1> 论坛 </h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // GET 请求

    // /student?current=1&limit=20
    @RequestMapping(path = "/student", method = RequestMethod.GET) // 指定路径和请求方式
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            // 更详细的参数注释 required = false 即可以不传入这个参数 defaultValue = "1" 即参数默认为 1
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // /student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    // post请求 // get请求传参时 参数是在路径上的 所以提交数据时通常不用get请求
    @RequestMapping(path="/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return"success";
    }

    // 响应HTML数据
    @RequestMapping(path="/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mdl = new ModelAndView();
        mdl.addObject("name", "杨某");
        mdl.addObject("age", "22");
        mdl.setViewName("demo/view");
        return mdl;
    }

    @RequestMapping(path="/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name", "湖北工业");
        model.addAttribute("age","44");
        return "demo/view";
    }


    // 响应JSON数据（异步请求）
    @RequestMapping(path="/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> mp = new HashMap<>();
        mp.put("name","杨某");
        mp.put("age",22);
        mp.put("salary",26000.00);
        return mp;
    }

    @RequestMapping(path="/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> lst = new ArrayList<>();
        Map<String, Object> mp = new HashMap<>();
        mp.put("name","杨某");
        mp.put("age",22);
        mp.put("salary",26000.00);
        lst.add(mp);

        mp = new HashMap<>();
        mp.put("name","杨某");
        mp.put("age",22);
        mp.put("salary",26000.00);
        lst.add(mp);

        mp = new HashMap<>();
        mp.put("name","杨某");
        mp.put("age",22);
        mp.put("salary",26000.00);
        lst.add(mp);
        return lst;
    }

    // cookie 示例
    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET) // 当Spring收到一个路径为/home的GET请求
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        //  创建cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        // 设置cookie生效的范围
        cookie.setPath("/community/alpha");
        // 设置cookie的生存时间
        cookie.setMaxAge(60 * 10);
        // 发送cookie
        response.addCookie(cookie);
        return "set cookie";
    }

    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET) // 当Spring收到一个路径为/home的GET请求
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) { // @CookieValue("code") String code注解的意思是从cookie中取key为code的值赋给code
        System.out.println(code);
        return "get cookie";
    }

    // session 示例
    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session) {// 服务器会自动创建 Session 和 response与model 类似
        session.setAttribute("id",1);
        session.setAttribute("name","Test" );
        return "set session";
    }

    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session) { // 这个session 也会从Spring MVC中自动注入
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }
}
