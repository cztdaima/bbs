package com.dhu.bbs.controller;


import com.dhu.bbs.Util.BbsUtil;
import com.dhu.bbs.model.Comment;
import com.dhu.bbs.model.Message;
import com.dhu.bbs.model.ViewObject;
import com.dhu.bbs.service.CommentService;
import com.dhu.bbs.service.MessageService;
import com.dhu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    CommentService CommentService;
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/message_comment"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String inform(Model model,
                         @CookieValue("ticket") String ticket,
                         @RequestParam("message_id") int message_id,
                         @RequestParam("describe") String describe

    ) {
            try{
                if(describe.length() > 0){

                    CommentService.comment(message_id,describe,ticket);
                    messageService.commentup(message_id);
                    model.addAttribute("code", "0");
                } else {
                    model.addAttribute("code", "1");
                }

            } catch (Exception e){

                model.addAttribute("code", "2");
            }
        return BbsUtil.Object2Json(model);
    }

}
