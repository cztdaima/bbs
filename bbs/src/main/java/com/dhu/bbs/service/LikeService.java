package com.dhu.bbs.service;

import com.dhu.bbs.Util.JedisAdapter;
import com.dhu.bbs.Util.JedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    JedisAdapter jedisAdapter;

    public int getLikeStatus(int userId,  int entityId){
        String likeKey = JedisKeyUtil.getLikeKey( entityId);
        if(jedisAdapter.sismember(likeKey, String.valueOf(userId))){
            return 1;
        }
        return 0;
    }

    public void  like(int userId, int entityId){
        String likeKey = JedisKeyUtil.getLikeKey(entityId);
        jedisAdapter.sadd(likeKey, String.valueOf(userId));

    }

    public long getLikeNum(int entityId){
        String likeKey = JedisKeyUtil.getLikeKey(entityId);
        return jedisAdapter.scard(likeKey);
    }

}
