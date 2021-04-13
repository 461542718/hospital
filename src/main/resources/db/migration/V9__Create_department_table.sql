create table department
(
	Dpmtid int auto_increment,
	Dpmtname varchar(32) not null,
	Dpmtinfo varchar(256) null,
	constraint department_pk
		primary key (Dpmtid)
);
alter table department auto_increment=40000;