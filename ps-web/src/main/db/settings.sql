-- check mysql time zone
show variables like '%time_zone%';
-- set time_zone https://blog.csdn.net/ZYC88888/article/details/86597674
set global time_zone = '+8:00';  ##修改mysql全局时区为北京时间，即我们所在的东8区
set time_zone = '+8:00';  ##修改当前会话时区
flush privileges;  #立即生效


-- change max_connections,default value is 151
-- todo 继续调参，mysqlslap并发4k mysql就直接挂了
show variables like '%connections%';
set global max_connections=10000;
flush privileges ;
