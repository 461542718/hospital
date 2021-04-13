create table doctor
(
	Did int auto_increment,
	Dname varchar(32) not null,
	Dsex int not null,
	Dtel varchar(32) null,
	Dlevel varchar(32) not null,
	hospital_id int not null,
	Dmajor int not null,
	Dphoto varchar(256) null,
	Dinfo varchar(256) null,
	constraint doctor_pk
		primary key (Did)
);
alter table doctor auto_increment=30000;