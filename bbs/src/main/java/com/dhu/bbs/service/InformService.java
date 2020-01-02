package com.dhu.bbs.service;

import com.dhu.bbs.Util.JedisAdapter;
import com.dhu.bbs.Util.JedisKeyUtil;
import com.dhu.bbs.dao.InformDao;
import com.dhu.bbs.model.Inform;
import com.dhu.bbs.model.InformView;
import com.dhu.bbs.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InformService {
    @Autowired
    InformDao informDao;

    @Autowired
    JedisAdapter jedisAdapter;

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    public void inform(int message_id, int userId) {
        Inform inform = new Inform();

        inform.setMessageId(message_id);
        inform.setInformUserId(userId);

        informDao.addInform(inform);
    }


    public int getInformStatus(int userId,  int entityId){
        String InformKey = JedisKeyUtil.getInformKey(entityId);
        if(jedisAdapter.sismember(InformKey, String.valueOf(userId))){
            return 1;
        }
        return 0;
    }

    public void  Inform(int userId, int entityId){
        String InformKey = JedisKeyUtil.getInformKey( entityId);
        jedisAdapter.sadd(InformKey, String.valueOf(userId));

    }

    public long getInformKey( int entityId){
        String InformKey = JedisKeyUtil.getInformKey( entityId);
        return jedisAdapter.scard(InformKey);
    }

    public int selectCount(){
        return informDao.selectCount();
    }

    public List<Inform> selectInform(int offset, int limit){
        return informDao.selectInform(offset,  limit);
    }

    public InformView setInformView(Inform inform){
        InformView informView = new InformView();
        Message message = messageService.getDetail(inform.getMessageId());
        informView.setInformId(inform.getId());
        informView.setInformUserId(inform.getInformUserId());
        informView.setInformUserName(userService.getUser(inform.getInformUserId()).getName());
        informView.setMessageImage(message.getImage());
        informView.setMessageTitle(message.getTitle());
        informView.setMessageUserId(message.getUser_id());
        informView.setMessageUserName(userService.getUser(message.getUser_id()).getName());
        informView.setMessageTitleId(inform.getMessageId());
        return informView;
    }

    public void deleteById(int message_id){
        informDao.deleteById(message_id);
    }
}
