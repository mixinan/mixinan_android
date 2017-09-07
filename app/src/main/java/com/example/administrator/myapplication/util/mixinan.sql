set names utf8;
drop database if exists mixinan;
create database mixinan character set utf8;
use mixinan;
create table mi_user(
	mi_user_id int primary key auto_increment,
	mi_user_name varchar(100) not null,
	mi_user_password varchar(100) not null,
	mi_user_gender varchar(5) default '1',
	mi_user_nickname varchar(30),
	mi_user_create_time varchar(20)
);

create table mi_blog(
	mi_blog_id int primary key auto_increment,
	mi_user_id int,
	mi_blog_text varchar(300),
	mi_blog_like_times int default 0,
	mi_blog_create_time varchar(20),
	mi_blog_last_modify_time varchar(20) default null
);



create table mi_pic(
	mi_pic_id int primary key auto_increment,
	mi_pic_url varchar(100),
	mi_pic_desc varchar(140),
	mi_pic_create_time varchar(20)
);


insert into mi_pic values
(null,"https://www.baidu.com/img/baidu_jgylogo3.gif","第一张",now()),
(null,"https://www.baidu.com/img/baidu_jgylogo3.gif","第二张",now());

select * from mi_pic;



insert into mi_user values(3,'123','123','1','万码千钧',now());
insert into mi_user values(5,'125','125','0','mixinan',now());
insert into mi_user values(6,'126','126','1','静版',now());

insert into mi_blog(mi_user_id,mi_blog_text,mi_blog_create_time) values
(6,'减肥!',now()),
(5,'ok!',now()),
(3,'yes!',now()),
(5,'no!',now()),
(3,'like!',now()),
(3,'do!',now());

select * from mi_user;

select * from mi_blog;

