alter table schedule drop column fee;

alter table schedule
	add time int not null;