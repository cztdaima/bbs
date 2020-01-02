package com.dhu.bbs;


import com.dhu.bbs.Util.BbsUtil;
import com.dhu.bbs.Util.JedisAdapter;
import com.dhu.bbs.dao.CommentDao;
import com.dhu.bbs.dao.LoginTicketDao;
import com.dhu.bbs.dao.MessageDao;
import com.dhu.bbs.dao.UserDao;
import com.dhu.bbs.model.Comment;
import com.dhu.bbs.model.LoginTicket;
import com.dhu.bbs.model.Message;
import com.dhu.bbs.model.User;
import com.dhu.bbs.service.CommentService;
import com.dhu.bbs.service.InformService;
import com.dhu.bbs.service.LikeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Sql("/init_schema.sql")
public class DhuBbsApplicationTests {
	@Autowired
	private UserDao userMapper;
	@Autowired
	private  LoginTicketDao loginTicketDao;

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private CommentService commentService;

	@Test
	public void contextLoads() {

		Random random = new Random();
		for(int i =1; i < 50; i++){
			Date date = new Date();
			User user = new User();
			user.setHead_url(String.format("http://images.nowcoder.com/head/%dt.png",random.nextInt(1000)));
			user.setName(String.format("user%d",i));
			user.setPassword("password");
			user.setSalt(".");
			if(i==1)
				user.setUser_limit(1);
			if(i==30)
				user.setUser_limit(1);
			else
				user.setUser_limit(0);

			userMapper.userAdd(user);

			user.setPassword(BbsUtil.MD5(user.getPassword()+user.getSalt()));
			userMapper.updatePassword(user);

			LoginTicket ticket = new LoginTicket();
			ticket.setStatus(0);
			ticket.setUser_id(i+1);

			ticket.setExpire(date);
			ticket.setTicket(String.format("ticket%d", i+1));
			loginTicketDao.addTicket(ticket);

		}

		for(int i=1;i <50; i++){
			for(int j=0; j<3;j++){
				Message message = new Message();
				message.setComment_count(3);
				Date date = new Date();
				date.setTime(date.getTime() + 1000*3600*5);
				message.setCreated_date(date);
				message.setImage(String.format("http://images.nowcoder.com/head/%dt.png",random.nextInt(1000)));
				message.setLike_count(i*j);
				message.setTitle("title" + String.format("%d", i+1));
				if(j%3==0)
					message.setM_section(1);
				else if(j%3==1)
					message.setM_section(2);
				else
					message.setM_section(3);
				message.setUser_id(i);
				message.setContent("This is content" + String.format("%d", i*j+1));
				messageDao.messageAdd(message);
			}
		}

		for(int i=1;i <=50; i++){
			for(int j=0; j<3;j++){
				for(int k=0;k<3;k++){
					Date date = new Date();
					Comment comment=new Comment();

					comment.setTitle_id(j+i*3);
					comment.setUser_id(50-i);
					comment.setComment("Comment" + String.format("%d", i+j+k));
					date = new Date();
					date.setTime(date.getTime() + 1000*3600*5);
					comment.setCreate_date(date);
					commentDao.addComment(comment);
				}
			}
		}






	}

	@Autowired
	private CommentDao commentDao;
	@Test
	public void comment(){
		for(int i=0; i<110;i++){
			for(int j=0;j<15;j++){
				Comment comment=new Comment();
				comment.setId(i);
				comment.setTitle_id(j);
				comment.setUser_id(100-i);
				comment.setComment("Comment" + String.format("%d", i*j+1));
				Date date = new Date();
				date.setTime(date.getTime() + 1000*3600*5);
				comment.setCreate_date(date);
				commentDao.addComment(comment);
			}

		}

	}

	@Test
	public void updateHeadUrl() {
		User user = userMapper.selectById(1);
		user.setPassword("p");
		user.setHead_url("fg");
		userMapper.updatePassword(user);
		userMapper.updateHeadUrl(user);
	}

	@Test
	public void test(){
		System.out.println(messageDao.getSearchPagingNews("1",1,10));

	}

	@Autowired
	JedisAdapter jedisAdapter;

	@Autowired
	InformService informService;

	@Test
	public void JedisTest(){
//		System.out.println(jedisAdapter.sadd("1","5"));  //添加
//		System.out.println(jedisAdapter.scard("1"));	//查看长度
//		System.out.println(jedisAdapter.sismember("1","55"));	//查看是否存在
//		System.out.println(jedisAdapter.srem("1","5"));		//删除
//		System.out.println(jedisAdapter.sadd("1","5"));
		System.out.println(jedisAdapter.scard("1"));
//		messageDao.likeupById(1,5);
//		System.out.println(informService.);
	}

}
