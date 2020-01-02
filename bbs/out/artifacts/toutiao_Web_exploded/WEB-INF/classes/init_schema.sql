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
 select2 varchar (128),
 date datetime,
 url varchar (128) ,
 describes varchar (128),
 email varchar (128),
 PRIMARY KEY(`id`)
);
