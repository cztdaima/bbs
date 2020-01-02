package com.dhu.bbs.controller;


import com.dhu.bbs.Util.BbsUtil;
import com.dhu.bbs.model.*;
import com.dhu.bbs.service.CommentService;
import com.dhu.bbs.service.InformService;
import com.dhu.bbs.service.MessageService;
import com.dhu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MessageService message_service;

    @Autowired
    CommentService comment_service;

    @Autowired
    InformService informService;

    @Autowired
    HostHolder hostHolder;

    @RequestMapping(value={"/request"})
    @ResponseBody
    public String request(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session){
        StringBuilder sb = new StringBuilder();
        Enumeration<String> headName = request.getHeaderNames();
        while(headName.hasMoreElements()){
            String name = headName.nextElement();
            sb.append(name + ":" + request.getHeader(name) + "<br>");
        }
        return sb.toString();
    }


    @RequestMapping(value = {"profile/{user_id}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String profile(@PathVariable("user_id") int user_id, Model model){

        model.addAttribute("userId", user_id);

        return "profile";
    }

    @RequestMapping(value = {"profileDetail/{user_id}"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String profileDetail(@PathVariable("user_id") int user_id){

        try {
        List<Message> messagesLists =  message_service.getLatestNews(user_id, 0, 50);
        List<MessageView> MessageViewList = new ArrayList<MessageView>();
        for(Message message:messagesLists){
            MessageView messageView = message_service.messageView(message);
            MessageViewList.add(messageView);

        }

        return BbsUtil.Object2Json(MessageViewList);
        } catch (Exception e) {
            return "出错了";
        }
    }



    @RequestMapping(value = "changeHead",method = RequestMethod.GET)
    public String changeHeadUrl() {

        return "changeHead";
    }

    @RequestMapping(value = {"changePost"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String changePost(Model model, @RequestParam("file") MultipartFile file, @CookieValue("ticket") String ticket) {

        try{
            Map<String, Object> map = userService.updateHeadUrl(file,ticket);
            if( map.isEmpty()){
                model.addAttribute("code","0") ;
                return "changeHead";
            } else {
                model.addAttribute("code","1") ;
                return "changeHead";
            }

        } catch (Exception e){
            model.addAttribute("code","2") ;
            return "changeHead";
        }
    }



    @RequestMapping(value = {"doChangePassword"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String doChangePassword(Model model, @RequestParam("password") String password, @CookieValue("ticket") String ticket) {

        try{
            Map<String, Object> map = userService.updatePassword(password,ticket);
            if( map.isEmpty()){
                model.addAttribute("code","0") ;

            } else {
                model.addAttribute("code","1") ;

            }

        } catch (Exception e){
            model.addAttribute("code","2") ;

        }
        return BbsUtil.Object2Json(model);
    }


}
