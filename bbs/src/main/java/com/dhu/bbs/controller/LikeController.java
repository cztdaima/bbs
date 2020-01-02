package com.dhu.bbs.controller;

import com.dhu.bbs.Util.BbsUtil;
import com.dhu.bbs.model.HostHolder;
import com.dhu.bbs.service.LikeService;
import com.dhu.bbs.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LikeController {
    @Autowired
    HostHolder hostHolder;
    @Autowired
    LikeService likeService;

    @Autowired
    MessageService messageService;

    @RequestMapping(value = {"doLike"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String doLike(Model model, @RequestParam("messageId") int messageId) {
        int userId= hostHolder.getUser().getId();

        if(likeService.getLikeStatus(userId, messageId) == 1){
            model.addAttribute("like", "1");

        } else {
            likeService.like(userId, messageId);
            model.addAttribute("like", "0");
        }
        try{
            messageService.likeup(messageId, Integer.parseInt(String.valueOf(likeService.getLikeNum(messageId))));
        }
        catch (Exception e){
            model.addAttribute("like", "2");
        }

        return BbsUtil.Object2Json(model);
    }

}
