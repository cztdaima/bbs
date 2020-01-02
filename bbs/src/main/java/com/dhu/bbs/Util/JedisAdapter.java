package com.dhu.bbs.Util;


import org.springframework.beans.factory.InitializingBean;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class JedisAdapter implements InitializingBean {
//    public static void print(int index, Object obj){
//        System.out.println(String.format("%d,%s", index, obj.toString()));
//    }
//
//    public static void main(String[] argv){
//        Jedis jedis = new Jedis();
//        jedis.flushAll();
//    }

    private JedisPool pool = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("localhost", 6379);

    }

    private Jedis getJedis(){
        return pool.getResource();
    }

    public long sadd(String key, String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();

            return jedis.sadd(key, value);
        } catch (Exception e){

            System.out.println("出错了");
            return 0;
        }

    }


    public long srem(String key, String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.srem(key, value);
        } catch (Exception e){

            System.out.println("出错了");
            return 0;
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }

    }


    public boolean sismember(String key, String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();

            return jedis.sismember(key, value);
        } catch (Exception e){
            System.out.println("出错了");

            return false;
        }
        finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }


    public long scard(String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.scard(key);
        } catch (Exception e){
            System.out.println("出错了");
            return 0;
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }

    }
}
