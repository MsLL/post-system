create database if not exists post;
use post;
-- CORR DESIGN
create table if not exists post(
    ID int primary key auto_increment,
    CONTENT varchar(5000) not null ,

    CREATE_DATETIME datetime not null ,
    UPDATE_DATETIME datetime on update now() not null
);

create table if not exists answer(
    ID int primary key auto_increment ,
    POST_ID int not null ,

    CONTENT varchar(5000) not null ,

    CREATE_DATETIME datetime not null ,
    UPDATE_DATETIME datetime on update now() not null
);

create table if not exists comment(
    ID int primary key auto_increment,
    PARENT_ID int,
    ROOT int not null ,
    ANSWER_ID int,

    CONTENT varchar(5000) not null ,

    CREATE_DATETIME datetime not null ,
    UPDATE_DATETIME datetime on update now() not null
);

-- user design
create table if not exists user(
    ID int primary key auto_increment ,
    NAME varchar(20) not null ,
    BIRTHDAY DATETIME,
    RESISTER_DATETIME datetime not null
);
insert into user(NAME,RESISTER_DATETIME) values ('A1',now()),('A2',now());

create table password_authenticate(
    ID int primary key auto_increment,
    USER_ID int not null unique ,
    PASSWORD varchar(20) not null
);
insert into password_authenticate(USER_ID, PASSWORD) values (1,'a1b23f2c'),(2,'c0932f32');

create table oauth_authenticate(
    ID int primary key auto_increment,
    USER_ID int not null unique ,
    OAUTH_NAME varchar(50) not null ,
    OAUTH_ID varchar(100) not null ,
    OAUTH_ACCESS_TOKEN varchar(100) not null ,
    OAUTH_EXPIRES DATETIME
);
insert into oauth_authenticate(user_id, oauth_name, oauth_id, oauth_access_token, oauth_expires)
values (1,'weibo','wb-012345','xxxxxxx',now()),
       (2,'qq','qq-234567','yyyyyyyyyy',now());


create table apikey_apisecret_authenticate(
    ID int primary key auto_increment ,
    USER_ID int not null unique ,
    API_KEY varchar(100) not null ,
    API_SECRET varchar(100) not null
);

insert into apikey_apisecret_authenticate(user_id, api_key, api_secret)
VALUES (1,'a-012345','xxxxxxxx'),
       (2,'a-234567','yyyyyyyy');