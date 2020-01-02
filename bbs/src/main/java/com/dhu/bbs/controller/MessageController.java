package com.dhu.bbs.controller;


import com.dhu.bbs.Util.BbsUtil;
import com.dhu.bbs.model.*;
import com.dhu.bbs.service.CommentService;
import com.dhu.bbs.service.InformService;
import com.dhu.bbs.service.MessageService;
import com.dhu.bbs.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    InformService informService;

    @RequestMapping(path = "/image", method = {RequestMethod.GET})
    @ResponseBody
    public void getImage(@RequestParam("name") String imageName,
                         HttpServletResponse response){
        System.out.println(imageName);
        response.setContentType("image/jepg");
        System.out.println(BbsUtil.IMAGE_DIR + imageName);
        try{
            StreamUtils.copy(new FileInputStream(
                            new File(BbsUtil.IMAGE_DIR + imageName)),
                    response.getOutputStream());
        } catch (Exception e){
            System.out.println("解析图片错误");
        }
    }


    @RequestMapping(value = {"title/{id}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String messageDetail(@PathVariable("id") int id, Model model){
        Message message =  messageService.getDetail(id);
        ViewObject vos = new ViewObject();
        vos.set("user", userService.getUser(message.getUser_id()));
        vos.set("news", message);

        List<Comment> commentResult =  commentService.selectCommentByTitle_id(id);
        List<ViewObject> cos = new ArrayList<>();
        for (Comment comment : commentResult){
            ViewObject co = new ViewObject();
            co.set("comment", comment);
            co.set("user",userService.getHead_urlById(comment.getUser_id()));
            cos.add(co);
        }
        model.addAttribute("cos", cos);
        model.addAttribute("vo", vos);
        return "message_detail";
    }

    @RequestMapping(value = {"searchResult"}, method = {RequestMethod.GET, RequestMethod.POST})

    public String searchResult(Map<Object,Object> map, Model model, @RequestParam("search") String search) {

        int pageTotalNumber =  messageService.selectMessageByTitleNum(search);

        int pageSize = 0;
        //得到总页数，每一页10条记录
        if(pageTotalNumber%10==0){
            pageSize=pageTotalNumber/10;
        }else{
            //有余数，那么总页数要+1
            pageSize=pageTotalNumber/10+1;
        }

        map.put("pageSize",pageSize);
        map.put("key",search);
        return "searchResult";
    }


    @RequestMapping(value = "getSearchPagingNews/{page}", method = RequestMethod.GET)
    @ResponseBody
    public String getSearchPagingNews(HttpServletRequest request, @PathVariable("page") int page,@RequestParam("search") String search,
                                      @RequestParam("maxPage") int maxpage) {
        if(page<0){
            page = 0;
        }
        if (page >= maxpage){
            page = maxpage-1;
        }

        try {


            List<Message> messagesLists = messageService.getSearchPagingNews(search,10*page,10);
            List<MessageView> MessageViewList = new ArrayList<MessageView>();
            for(Message message:messagesLists){
                MessageView messageView = messageService.messageView(message);
                MessageViewList.add(messageView);
            }

            return BbsUtil.Object2Json(MessageViewList);


        } catch (Exception e) {
            return "出错了";
        }

    }



    @RequestMapping(value = "search",method = RequestMethod.GET)
    public String search() {
        return "search";
    }

    @RequestMapping(value = "post",method = RequestMethod.GET)
    public String getPost() {
        return "post";
    }

    @RequestMapping(value = {"post_page"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String post(Model model, @RequestParam("select2") int select2,
                       @RequestParam("file") MultipartFile file,
                       @RequestParam("describe") String describe,
                       @RequestParam("title") String title,
                       @CookieValue("ticket") String ticket
    ) {

        try{
            Map<String, Object> map = userService.addMessage(select2, file, describe, title, ticket);
            if( map.isEmpty()){
                model.addAttribute("code","0") ;
                return "post";
            } else {
                model.addAttribute("code","1") ;
                return "post";
            }

        } catch (Exception e){
            model.addAttribute("code","2") ;
            return "post";
        }
    }

    @RequestMapping(value = {"/message_delete"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String inform(Model model,
                         @RequestParam("user_id") int user_id,
                         @RequestParam("message_id") int message_id,
                         @RequestParam("submit") String submit
    ) {
        try{
            if(hostHolder.getUser().getId()==user_id){
                messageService.deleteById(message_id);
                informService.deleteById(message_id);
            }
            else{
                model.addAttribute("code",1);

            }
        } catch (Exception e){
            model.addAttribute("code",2);

        }
        return "redirect:/profile/"+user_id;
    }

    @RequestMapping(value = {"/admin_delete"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String delete(Model model,
                         @RequestParam("message_id") int message_id

    ) {
        try{

            messageService.deleteById(message_id);
            informService.deleteById(message_id);
            return "redirect:/admin";
        } catch (Exception e){
            return "error";
        }

    }

    @RequestMapping(value = "findLogPageMonth/{page}", method = RequestMethod.GET)
    @ResponseBody
    public String getInfo(HttpServletRequest request, @PathVariable("page") int page, @RequestParam("maxPage") int maxpage) {
       if(page<0){
           page = 0;
       }
        if (page >= maxpage){
            page = maxpage-1;
        }

        try {

            List<Message> messagesLists = messageService.getPagingNews(10*page,10);
            List<MessageView> MessageViewList = new ArrayList<MessageView>();
            for(Message message:messagesLists){
                MessageView messageView = messageService.messageView(message);
                MessageViewList.add(messageView);
            }
            return BbsUtil.Object2Json(MessageViewList);


        } catch (Exception e) {
            return "出错了";
        }

    }

    @RequestMapping(value = "findSection/{m_section}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public String findSection(HttpServletRequest request, @PathVariable("page") int page,@PathVariable("m_section") int m_section) {

        try {
            List<Message> messagesLists = messageService.getSectionNews(m_section,10*page,10);
            List<MessageView> MessageViewList = new ArrayList<MessageView>();
            for(Message message:messagesLists){
                MessageView messageView = messageService.messageView(message);
                MessageViewList.add(messageView);
            }
            return BbsUtil.Object2Json(MessageViewList);


        } catch (Exception e) {
            return "出错了";
        }


    }

    @RequestMapping(value = {"section/{section}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String section(@PathVariable("section") int section, Model model, Map<Object,Object> map){
        int pageTotalNumber= messageService.MessageSectionCount(section);
        int pageSize = 0;
        //得到总页数，每一页10条记录
        if(pageTotalNumber%10==0){
            pageSize=pageTotalNumber/10;
        }else{
            //有余数，那么总页数要+1
            pageSize=pageTotalNumber/10+1;
        }
        map.put("section", section);
        map.put("pageSize",pageSize);
        return "section";
    }

}
