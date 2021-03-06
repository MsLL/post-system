create database if not exists post;
use post;
-- CORR DESIGN
create table if not exists post(
    ID int primary key auto_increment,
    USER_ID int not null,
    CONTENT varchar(5000) not null ,

    STATE varchar(20) not null ,
    CREATE_DATETIME datetime not null default now(),
    UPDATE_DATETIME datetime on update now()
);
insert into post(USER_ID,CONTENT,STATE)
    values (1,'Apisex Wwa is on Facebook. Join Facebook to connect with Apisex Wwa and others you may know. Facebook gives people the power to share and makes the ...','ARCHIVED'),
           (2,'Jenkins – an open source automation server which enables developers around the world to reliably build, test, and deploy their software.','ACTIVE'),
           (3,'Simplify API development for users, teams, and enterprises with our open source and professional toolset. Find out how Swagger can help you and get started ...','ACTIVE');
create table if not exists answer(
    ID int primary key auto_increment ,
    USER_ID int not null,
    POST_ID int not null ,

    CONTENT varchar(5000) not null ,

    STATE varchar(20) not null ,
    CREATE_DATETIME datetime not null default now(),
    UPDATE_DATETIME datetime on update now()
);
insert into answer(USER_ID, POST_ID, CONTENT, STATE)
    values (1,1,'aaa','ACTIVE');

create table if not exists comment(
    ID int primary key auto_increment,
    USER_ID int not null,
    PARENT_ID int,
    ROOT int not null ,
    ANSWER_ID int,

    CONTENT varchar(5000) not null ,

    STATE varchar(20) not null ,
    CREATE_DATETIME datetime not null default now(),
    UPDATE_DATETIME datetime on update now()
);
insert into comment(USER_ID, ROOT, ANSWER_ID, CONTENT, STATE)
values (1,1,1,'cccc','ACTIVE');

-- user design
create table if not exists user(
    ID int primary key auto_increment ,
    NAME varchar(20) unique not null  ,
    PHONE_NUMBER varchar(15),
    BIRTHDAY DATETIME,
    SALT varchar(20),
    CREATE_DATETIME datetime not null default now(),-- may used as register datetime
    UPDATE_DATETIME datetime on update now()
);
insert into user(NAME,SALT,CREATE_DATETIME,UPDATE_DATETIME) values ('A1','tbRXq5',now(),now()),('A2','ByWF8j',now(),now());

-- 这张表也可以直接放在user表里面
create table password_authenticate(
    ID int primary key auto_increment,
    USER_ID int not null unique ,
    PASSWORD varchar(50) not null,
    CREATE_DATETIME datetime not null default now(),
    UPDATE_DATETIME datetime on update now()
);
insert into password_authenticate(USER_ID, PASSWORD) values (1,'c32821ff6a95c21320f0046f495ad9f7'),(2,'c0932f32');

create table oauth_authenticate(
    ID int primary key auto_increment,
    USER_ID int not null unique ,
    OAUTH_NAME varchar(50) not null ,
    OAUTH_ID varchar(100) not null ,
    OAUTH_ACCESS_TOKEN varchar(100) not null ,
    OAUTH_EXPIRES DATETIME,
    CREATE_DATETIME datetime not null default now(),
    UPDATE_DATETIME datetime on update now()
);
insert into oauth_authenticate(user_id, oauth_name, oauth_id, oauth_access_token, oauth_expires)
values (1,'weibo','wb-012345','xxxxxxx',now()),
       (2,'qq','qq-234567','yyyyyyyyyy',now());


create table apikey_apisecret_authenticate(
    ID int primary key auto_increment ,
    USER_ID int not null unique ,
    API_KEY varchar(100) not null ,
    API_SECRET varchar(100) not null,
    CREATE_DATETIME datetime not null default now(),
    UPDATE_DATETIME datetime on update now()
);
insert into apikey_apisecret_authenticate(user_id, api_key, api_secret)
VALUES (1,'a-012345','xxxxxxxx'),
       (2,'a-234567','yyyyyyyy');