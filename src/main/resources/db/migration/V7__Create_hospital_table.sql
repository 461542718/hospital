create table hospital
(
	hospital_id int auto_increment,
	hospital_name varchar(64) not null,
	hospital_addr varchar(256) not null,
	hospital_tel varchar(32) not null,
	hospital_level varchar(24) not null,
	hospital_major varchar(256) null,
	hospital_photo varchar(256) null,
	hospital_info varchar(256) null,
	constraint hospital_pk
		primary key (hospital_id)
);
alter table hospital auto_increment=20000;