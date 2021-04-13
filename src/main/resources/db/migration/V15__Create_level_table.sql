create table level
(
	id int auto_increment,
	Dlevel int not null,
	Lname varchar(24) not null,
	fee varchar(32)  not null,
	constraint level_pk
		primary key (id)
);
