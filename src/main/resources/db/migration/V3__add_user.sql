create table user(
    id int primary key auto_increment,
    username varchar(64) unique not null ,
    email varchar(255) unique not null ,
    password varchar(64) not null ,
    user_roles varchar(10) not null
);