create table if not exists answer
(
    ID              int primary key auto_increment,
    USER_ID         int not null,
    POST_ID         int not null,

    CONTENT         varchar(5000) not null,

    STATE           varchar(20) not null,
    CREATE_DATETIME datetime not null default now(),
    UPDATE_DATETIME datetime on update now()
);