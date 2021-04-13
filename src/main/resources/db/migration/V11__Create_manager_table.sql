create table manager
(
	Mnumber int ,
	username varchar(256) not null,
	password varchar(256) not null,
	token varchar(32) null,
	constraint manager_pk
		primary key (Mnumber)
);
