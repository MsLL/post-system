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
