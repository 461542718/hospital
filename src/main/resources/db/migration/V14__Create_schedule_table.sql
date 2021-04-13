create table schedule
(
	Scid int auto_increment,
	week int not null,
	hospital_id int null,
	Did int not null,
	Dmajor int null,
	register_limited int not null,
	fee varchar(32) not null,
	constraint schedule_pk
		primary key (Scid)
);