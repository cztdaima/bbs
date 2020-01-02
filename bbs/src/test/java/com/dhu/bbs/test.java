package com.dhu.bbs;


import com.dhu.bbs.dao.MessageDao;
import com.dhu.bbs.service.MessageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 默认 spring 链接数据库的方式
 * @author TongZhou
 *
 */
public class test {

    /**
     * 使用 Spring 默认的数据库方式
     */
    @Test
    public void JDBCTest(){

        //创建数据库链接的数据源
        DriverManagerDataSource dataSource=new DriverManagerDataSource();

        //设置数据库的链接信息
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///dhubbs?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
        jdbcTemplate.execute("create table user5(id int primary key auto_increment,name varchar(20))");
    }

    @Autowired
    private MessageService messageService;

    @Test
    public void test(){
        System.out.println(messageService.getSearchPagingNews("1",0,10));


}
}

