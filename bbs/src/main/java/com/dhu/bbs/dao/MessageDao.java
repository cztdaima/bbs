package com.dhu.bbs.dao;

import com.dhu.bbs.model.Message;
import com.dhu.bbs.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageDao {
    String TABLE_NAME = "message";
    String INSERT_FIELDS = " title, image, like_count, comment_count, created_date, user_id, m_section, content";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
    ") values (#{title},#{image},#{like_count},#{comment_count}, #{created_date}, #{user_id}, #{m_section}, #{content})" })
    void messageAdd(Message message);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME, "where id=#{id}"})
    Message selectById(int id);

    @Update({"update",TABLE_NAME, " set like_count=#{likeCount} where id=#{id}"})
    void likeupById( @Param("id") int id, @Param("likeCount") int likeCount);

    @Update({"update",TABLE_NAME, " set comment_count=comment_count+1 where id=#{id}"})
    void commentupById(int id);

//    @Update({"update", TABLE_NAME, " set password=#{password} where name=#{name}"})
//    void updatePassword(User user);
//
//    @Delete({"delete from", TABLE_NAME, "WHERE name=#{name}"})
//   void deleteByName(User user);
//
//    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME, "where id=#{id}"})
//    User selectById(int id);
//

    @Select({"select * from", TABLE_NAME, "where title like \"%\"#{name}\"%\" "})
    List<Message> selectMessageByTitle(String name);

     List<Message> findByName(@Param("title") String title);

    List<Message> selectByUserIdAndOffset(@Param("user_id") int user_id , @Param("offset") int offset,
    @Param("limit") int limit);

    List<Message> selectBySectionAndOffset(@Param("m_section") int m_section , @Param("offset") int offset,
                                          @Param("limit") int limit);

    @Delete({"delete from", TABLE_NAME , "where id=#{id}"})
    void deleteById(int id);

    @Select({"select count(*) from", TABLE_NAME})
    int MessageCount();

    @Select({"select count(*) from", TABLE_NAME + " where m_section=#{m_section}"})
    int MessageSectionCount(int m_section);

    List<Message> getPagingNews( @Param("offset") int offset, @Param("limit") int limit);

    List<Message> getSectionNews(@Param("m_section") int m_section , @Param("offset") int offset,
                                          @Param("limit") int limit);

    List<Message> getSearchPagingNews(@Param("title") String title , @Param("offset") int offset,
                                 @Param("limit") int limit);

    @Select({"select count(*) from", TABLE_NAME, "where title like \"%\"#{title}\"%\" "})
    int selectMessageByTitleNum(String title);
}