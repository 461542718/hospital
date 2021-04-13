

alter table user drop primary key;

alter table user
    add constraint user_pk
        primary key (Cardnumber);
alter table user modify Cardnumber int auto_increment;
alter table user auto_increment=10000;