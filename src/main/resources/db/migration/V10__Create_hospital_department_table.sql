create table hospital_department
(
	id int auto_increment,
	hospital_id int not null,
	Dpmtid int not null,
	constraint hospital_department_pk
		primary key (id)
);