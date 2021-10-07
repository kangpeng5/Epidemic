package com.cov19.epidemic.controller;

import com.cov19.epidemic.bean.*;
import com.cov19.epidemic.service.EpidemicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/epidemicData")
public class EpidemicController {
    @Autowired
    private EpidemicService epidemicService;

    //录入疫情数据
    @PostMapping("/ajax/input")
    @ResponseBody
    public AjaxResponseInfo inputData(@RequestBody DailyEpidemicInfo dailyEpidemicInfo, Model model, HttpSession session){
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        //从session中获取当前登录系统的用户信息
        UserInfo user = (UserInfo) session.getAttribute("loginUser");
        if (user == null){
            responseInfo.setCode(-2);
            responseInfo.setMsg("你还没有登录");
        }else {
            //将数据保存
            List<ProvinceInfo> list = epidemicService.saveData(dailyEpidemicInfo,user.getUserId());
            responseInfo.setData(list);
        }
        return responseInfo;
    }

    //查询最新疫情信息
    @GetMapping("/ajax/latestData")
    @ResponseBody
    public AjaxResponseInfo findLatestData(){
        AjaxResponseInfo responseInfo = new AjaxResponseInfo();
        List<EpidemicDetailInfo> list = epidemicService.findLatestData();
        responseInfo.setData(list);
        return responseInfo;
    }
}
