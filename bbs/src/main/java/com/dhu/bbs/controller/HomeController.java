package com.dhu.bbs.controller;

import com.dhu.bbs.Util.BbsUtil;
import com.dhu.bbs.model.HostHolder;
import com.dhu.bbs.model.Message;
import com.dhu.bbs.model.ViewObject;
import com.dhu.bbs.service.MessageService;
import com.dhu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    MessageService message_service;

    @Autowired
    UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path= {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model, Map<Object,Object> map,
                        @RequestParam(value = "pop",defaultValue = "0") int pop) {
        int pageTotalNumber= message_service.MessageCount();

        int pageSize = 0;
        //得到总页数，每一页10条记录

        if(pageTotalNumber%10==0){
            pageSize=pageTotalNumber/10;
        }else{
            //有余数，那么总页数要+1
            pageSize=pageTotalNumber/10+1;
        }

        map.put("pageSize",pageSize);
        map.put("pop",pop);

        return "index";
    }




    @RequestMapping("/admin")
    public String admin(Map<Object,Object> map,@RequestParam(value = "pop",defaultValue = "0") int pop){
        int pageTotalNumber= message_service.MessageCount();
        int pageSize = 0;
        //得到总页数，每一页10条记录
        if(pageTotalNumber%10==0){
            pageSize=pageTotalNumber/10;
        }else{
            //有余数，那么总页数要+1
            pageSize=pageTotalNumber/10+1;
        }

        map.put("pageSize",pageSize);
        map.put("pop",pop);
        return "admin";
    }


    @RequestMapping("/logins")
    public String login(){
        return "login";
    }

    @RequestMapping("/section")
    public String sction(){
        return "section";
    }

    @RequestMapping("/header")
    public String header(Map<Object,Object> map){

        String loginUser = BbsUtil.Object2Json(hostHolder.getUser());
        map.put("loginUser",loginUser);
        return "header";
    }

    @RequestMapping("/footer")
    public String footer(){
        return "footer";
    }

//    @RequestMapping("/back")
//    public String back(){
//        return "back";
//    }
}