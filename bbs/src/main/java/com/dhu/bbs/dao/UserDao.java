package com.dhu.bbs.dao;

import com.dhu.bbs.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
public interface UserDao {
    String TABLE_NAME = "user";
    String INSERT_FIELDS = " name, password, salt, head_url, user_limit";
    String SELECT_FIELDS = " id, name, password, salt , head_url, user_limit";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
    ") values (#{name},#{password},#{salt},#{head_url},#{user_limit})" })
    void userAdd(User user);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME, "where name=#{name}"})
    User selectByName(String username);

    @Update({"update", TABLE_NAME, " set password=#{password} where name=#{name}"})
    void updatePassword(User user);

    @Delete({"delete from", TABLE_NAME, "WHERE name=#{name}"})
    void deleteByName(User user);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME, "where id=#{id}"})
    User selectById(int id);

    @Select({"select id from", TABLE_NAME, "where name=#{name}"})
    int selectIDByName(String name);

    @Update({"update", TABLE_NAME, " set head_url=#{head_url} where id=#{id}"})
    void updateHeadUrl(User user);

    @Select({"select head_url from", TABLE_NAME, "where id=#{id}"})
    String getHead_urlById(int id);

}