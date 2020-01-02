package com.dhu.bbs.controller;


import com.dhu.bbs.Util.BbsUtil;
import com.dhu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;



@Controller
public class LoginController {
    @Autowired

    private UserService UserService;



    @RequestMapping(value = "reg_page",method = RequestMethod.GET)
    public String get_Reg() {
        return "reg_page";
    }



    @RequestMapping(value = {"reg"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String reg(Model model, @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      HttpServletResponse response
    ) {

        try{
            Map<String, Object> map = UserService.register(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                model.addAttribute("code","0") ;
                response.addCookie(cookie);
                return "redirect:/";
            } else {
                model.addAttribute("coder",map);
                return "reg_page";
            }
        } catch (Exception e){
            model.addAttribute("code","1") ;
            return "reg_page";
        }

    }


    @RequestMapping(value = {"login"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String login(Model model, @RequestParam("username") String username,
                      @RequestParam("password") String password,

                        HttpServletResponse response
    ) {

        try{
            Map<String, Object> map = UserService.login(username, password);
            if(map.containsKey("userLimit")){
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);

                model.addAttribute("code",0);
                return BbsUtil.Object2Json(model);
            }
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                model.addAttribute("code",1);
                return BbsUtil.Object2Json(model);
            } else {
                model.addAttribute("code",2);
                return BbsUtil.Object2Json(model);
            }
        } catch (Exception e){

            model.addAttribute("code",2);
            return BbsUtil.Object2Json(model);
    }
    }


    @RequestMapping(value = {"logout"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(@CookieValue("ticket") String ticket){
        UserService.logout(ticket);
        return "redirect:/";
    }


}
