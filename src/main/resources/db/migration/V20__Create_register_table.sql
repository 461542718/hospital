create table register
(
	Rid int auto_increment,
	cardnumber int not null,
	hospital_id int not null,
	Did int not null,
	Dmajor int not null,
	registerDate long not null,
	Scid int not null,
	visitDate long null,
	Rstatus int not null,
	constraint register_pk
		primary key (Rid)
);