package com.dhu.bbs.service;

import com.dhu.bbs.Util.BbsUtil;
import com.dhu.bbs.dao.InformDao;
import com.dhu.bbs.dao.LoginTicketDao;
import com.dhu.bbs.dao.UserDao;
import com.dhu.bbs.model.Inform;
import com.dhu.bbs.model.LoginTicket;
import com.dhu.bbs.model.Message;
import com.dhu.bbs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;


@Service
@SuppressWarnings("unchecked")
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginTicketDao LoginTicketDao;

    @Autowired
    private InformDao informDao;

    @Autowired
    private MessageService message_service;


    public void userAdd(User user){
        userDao.userAdd(user);
        }

    public Map<String, Object> register(String username, String password){
        Map map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(username)) {
            map.put("msgname", "用户名不能为空");
            return map;
        }
        if (StringUtils.isEmpty(password)) {
            map.put("msgpassword","密码不能为空");
            return map;
        }
        User user = userDao.selectByName(username);

        if(user != null){
            map.put("msgname", "用户名已经被注册");
            return map;
        }

        Random random = new Random();
        user = new User();
        user.setName(username);
        user.setHead_url(String.format("http://images.nowcoder.com/head/%dt.png",random.nextInt(1000)));
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        user.setPassword(BbsUtil.MD5(password+user.getSalt()));
        userDao.userAdd(user);

        String ticket = addLoginTicket(userDao.selectIDByName(username));
        map.put("ticket", ticket);

        return map;
    }

    public Map<String, Object> login(String username, String password) {
        Map map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(username)) {
            map.put("msgname", "用户名不能为空");
            return map;
        }
        if (StringUtils.isEmpty(password)) {
            map.put("msgpassword", "密码不能为空");
            return map;
        }
        User user = userDao.selectByName(username);

        if (user == null) {
            map.put("msgname", "用户名未注册");
            return map;
        }

        if (!BbsUtil.MD5(password + user.getSalt()).equals(
                user.getPassword())) {
            map.put("msgpassword", "密码错误");
            return map;
        }
        if(user.getUser_limit()==1){
            map.put("userLimit",user.getUser_limit());
        }
        String ticket = addLoginTicket(user.getId());

        map.put("ticket", ticket);

        return map;
    }

    private String addLoginTicket(int user_id){

        LoginTicket ticket = new LoginTicket();
        ticket.setUser_id(user_id);
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        ticket.setExpire(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replace("-", ""));
        LoginTicketDao.addTicket(ticket);

        return ticket.getTicket();
    }

    public User getUser(int id){
        return userDao.selectById(id);
    }

    public void logout(String ticket){
        LoginTicketDao.updateStatus(ticket, 1);
    }




    public Map<String, Object> addMessage(int select2, MultipartFile file, String describe, String title, String ticket){
        Map<String, Object> map = new HashMap<>();

        try {
            LoginTicket loginTicket= LoginTicketDao.selectByTicket(ticket);
            if(loginTicket.getStatus()==1){
                map.put("msg", "更改失败");
                return map;
            }
            String url = BbsUtil.saveImage(file);
            Message message = new Message();
            message.setTitle(title);
            message.setLike_count(0);
            message.setContent(describe);
            message.setComment_count(0);
            Date date = new Date();
            message.setImage(url);
            message.setM_section(select2);
            message.setCreated_date(date);

            message.setUser_id(loginTicket.getUser_id());
            message_service.addMessage(message);
        }
        catch (Exception e){
            map.put("msgImage", "上传失败");
            return map;
        }
        return map;
    }

    public Map<String, Object> updateHeadUrl(MultipartFile file, String ticket) {
        Map<String, Object> map = new HashMap<>();

        try {
            String url = BbsUtil.saveImage(file);
            LoginTicket loginTicket= LoginTicketDao.selectByTicket(ticket);
            if(loginTicket.getStatus()==1){
                map.put("msg", "更改失败");
                return map;
            }
            User user = userDao.selectById(loginTicket.getUser_id());
            user.setHead_url(url);
            userDao.updateHeadUrl(user);
        }
        catch (Exception e){
            map.put("msgImage", "上传失败");
            return map;
        }

        return map;
    }

    public Map<String, Object> updatePassword (String password, String ticket){
        Map<String, Object> map = new HashMap<>();

        try {

            LoginTicket loginTicket= LoginTicketDao.selectByTicket(ticket);
            if(loginTicket.getStatus()==1){
                map.put("msg", "更改失败");
                return map;
            }
            User user = userDao.selectById(loginTicket.getUser_id());
            user.setPassword(BbsUtil.MD5(password+user.getSalt()));

            userDao.updatePassword(user);
        }
        catch (Exception e){
            map.put("msg", "更改失败");
            return map;
        }

        return map;
    }
    public String getHead_urlById(int id) {return userDao.getHead_urlById(id);}
}