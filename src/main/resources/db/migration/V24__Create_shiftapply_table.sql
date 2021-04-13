create table shiftapply
(
	id int auto_increment,
	content varchar(256) not null,
	note varchar(256) null,
	gmt_create bigint null,
	gmt_modify int null,
	status int default 0 not null,
	constraint shiftapply_pk
		primary key (id)
);