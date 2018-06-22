drop database flychat if exists;
create database `flychat`;

use `flychat`;

create table `user`(
	user_id int(11) primary key auto_increment,
	user_name varchar(20),
	password varchar(20),
	nick_name varchar(20)
);
insert into `user`(user_name,password,nick_name) values('root','liguang','administrator');
insert into `user`(user_name,password,nick_name) values('liguang','liguang','user');

create table `leaveMsg`(
	msg_id int(11) primary key auto_increment,
	content text,
	send_id int(11),
	receive_id int(11),
	has_read varchar(1) default 'N',
	foreign key (send_id) references `user`(user_id),
	foreign key (receive_id) references `user`(user_id)
);

insert into `leaveMsg`(content,send_id,receive_id) values('hello',2,1);
insert into `leaveMsg`(content,send_id,receive_id) values('hello',2,1);

