create table notice
(
	noticeid int auto_increment,
	title varchar(50) not null,
	description text not null,
	gmt_create bigint null,
	gmt_modified bigint null,
	creator int null,
	constraint notice_pk
		primary key (noticeid)
);