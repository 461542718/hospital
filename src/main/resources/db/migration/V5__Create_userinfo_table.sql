create table userinfo
(
	Cardnumber int not null,
	Tel varchar(24) null,
	address varchar(128) null,
	Sex varchar(24) not null,
	Briefinfo text null,
	Uphoto varchar(256) null,
	constraint userinfo_pk
		primary key (Cardnumber)
);