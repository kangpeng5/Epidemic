package com.cov19.epidemic.controller;

import com.cov19.epidemic.bean.UserInfo;
import com.cov19.epidemic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    //登录
    //user:封装前台请求数据，前提是表单name中的值与user属性中的值一致
    //model:将后台数据传递给前台
    //session:传统session，后台缓存
    @RequestMapping("/login")
    public String login(UserInfo user, Model model, HttpSession session){
        //获取用户信息
        UserInfo u = userService.findByAccount(user);
        if (u==null){
            //账号不正确
            model.addAttribute("msg","账号不正确!");
            return "login";
        }
        if (u.getPassword().equals(user.getPassword())){
            //登录成功
            //将当前用户信息保存到session中
            session.setAttribute("loginUser", u);
            return "redirect:/main.jsp";
        }
        model.addAttribute("msg","账号不正确");
        return "login";
    }
    //退出登录
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //清理session
        session.invalidate();
        return "redirect:epidemic.jsp";
    }
}
