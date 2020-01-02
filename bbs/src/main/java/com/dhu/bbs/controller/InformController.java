package com.dhu.bbs.controller;

import com.dhu.bbs.Util.BbsUtil;
import com.dhu.bbs.model.*;
import com.dhu.bbs.service.InformService;
import com.dhu.bbs.service.MessageService;
import com.dhu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class InformController {
    @Autowired
    HostHolder hostHolder;

    @Autowired
    InformService informService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/inform"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String inform(Model model,
                         @RequestParam("message_id") int message_id
    ) {
        try{
            int userId = hostHolder.getUser().getId();

            if(informService.getInformStatus(userId, message_id) == 1) {
                model.addAttribute("inform", "1");
            }
            else{
                informService.Inform(userId, message_id);
                informService.inform(message_id, userId);
                model.addAttribute("inform","0") ;
            }

        } catch (Exception e){
            model.addAttribute("inform","2") ;
        }
        return BbsUtil.Object2Json(model);

    }


    @RequestMapping("/informManage")
    public String informManage(Map<Object,Object> map){
        int pageTotalNumber= informService.selectCount();
        int pageSize = 0;
        //得到总页数，每一页10条记录
        if(pageTotalNumber%10==0){
            pageSize=pageTotalNumber/10;
        }else{
            //有余数，那么总页数要+1
            pageSize=pageTotalNumber/10+1;
        }

        map.put("pageSize",pageSize);

        return "informManage";
    }

    @RequestMapping(value = "findInform/{page}", method = RequestMethod.GET)
    @ResponseBody
    public String findInform(Model model,HttpServletRequest request, @PathVariable("page") int page) {
        try{

            List<Inform> informs = informService.selectInform(10*page,10);

            List<InformView> InformViewList = new ArrayList<InformView>();
            for (Inform inform : informs){

                InformViewList.add(informService.setInformView(inform));

            }

            return BbsUtil.Object2Json(InformViewList);

        } catch (Exception e){
            return "出错了";
        }

    }


    @RequestMapping(value = "/adminDeleteInform", method = RequestMethod.POST)
    public String adminDeleteInform(@RequestParam("message_id") int message_id,
                                    @RequestParam("inform_id") int inform_id){


        try{

            messageService.deleteById(message_id);
            informService.deleteById(message_id);
            return "redirect:/informManage";
        } catch (Exception e){
            return "error";
        }


    }

}
