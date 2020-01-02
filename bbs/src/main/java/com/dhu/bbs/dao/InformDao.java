package com.dhu.bbs.dao;

import com.dhu.bbs.model.Inform;

import com.dhu.bbs.model.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InformDao {
    String TABLE_NAME = "inform";
    String INSERT_FIELDS = "messageId, informUserId";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{messageId},#{informUserId})"})
    void addInform(Inform inform);

    @Select({"select count(*) from", TABLE_NAME})
    int selectCount();

    List<Inform> selectInform(@Param("offset") int offset, @Param("limit") int limit);

    @Delete({"delete from", TABLE_NAME , "where messageId=#{message_id}"})
    void deleteById(int message_id);
}
