drop table if exists user;

CREATE TABLE user(
id INT unsigned not null AUTO_INCREMENT,
name VARCHAR(64) default '',
password varchar(128) not null default '',
salt varchar(32) not null default '',
head_url varchar(256) not null default '',
user_limit int(11) not null default '0',
PRIMARY KEY(`id`)
 ) ENGINE=INNODB;


DROP TABLE IF EXISTS message;

create table message(
	id INT unsigned not null AUTO_INCREMENT,
  title varchar(64) not null default '',
	image varchar(64) not null default '',
	like_count int not null,
	comment_count int(11) not null,
	created_date datetime not null,
	user_id int (11) not null,
	m_section int(11) not null,
	content  varchar(1024) not null default '',
	PRIMARY KEY(`id`)
)	ENGINE=INNODB;

DROP TABLE IF EXISTS login_ticket;

CREATE TABLE login_ticket(
 id INT unsigned not null AUTO_INCREMENT,
 user_id INT NOT NULL,
 ticket varchar (45) not null,
 expire datetime not null,
 status int null default 0,
 PRIMARY KEY(`id`),
 UNIQUE INDEX `ticket_UNIQUE` (`ticket` ASC)
);

DROP TABLE IF EXISTS inform;

CREATE TABLE inform(
 id INT unsigned not null AUTO_INCREMENT,
 informUserId int null,
 messageId int null,
 PRIMARY KEY(`id`)
);

DROP TABLE IF EXISTS comment;

CREATE TABLE comment(
id int unsigned not null AUTO_INCREMENT,
title_id int not null,
comment varchar(128) default '',
create_date datetime not null,
user_id int not null,
primary  key (`id`)
)ENGINE=INNODB;
