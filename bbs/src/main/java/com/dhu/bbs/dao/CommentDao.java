package com.dhu.bbs.dao;

import com.dhu.bbs.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentDao {
    String TABLE_NAME = "comment";
    String INSERT_FIELDS = " title_id,comment,create_date,user_id";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values (#{title_id},#{comment},#{create_date},#{user_id})"})
    void addComment(Comment comment);

    @Select({"select * from", TABLE_NAME, "where title_id = #{id}"})
    List<Comment> selectCommentByTitle_id(int id);
}