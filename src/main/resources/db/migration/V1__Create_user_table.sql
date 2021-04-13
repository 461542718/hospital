create table user
(
	username varchar(256) ,
	password varchar(256) null,
	Cardnumber varchar(256) null,
	Idcard varchar(256) null,
	constraint users_pk
		primary key (username)
);