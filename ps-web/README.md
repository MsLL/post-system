# quick start

## dev

## prod

prod用docker-compose部署。

##### 1.打jar包

```shell
#pwd: ~/work_spaces/post-system
mvn clean package -Dmaven.test.skip=true -P prod
```

##### 2.容器化应用(打成镜像)

```shell
#pwd: /work_spaces/post-system/ps-web
docker build -t upupdididi/post-web-service:v1.0.0 .
```

##### 3.启系统

```shell
#pwd: /work_spaces/post-system/ps-web
docker-compose up -d
```

如果web-service挂了，docker logs containerId看一下，mysql连不上导致启动失败可能是mysql的post数据库没建。

如果elasticsearch挂了，docker logs containerId看一下，用docker-compose启的时候，ubuntu18.04有如下报错

error1.

```
 max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
```

https://stackoverflow.com/a/57998152

##### 4.关闭系统

```shell
#pwd: /work_spaces/post-system/ps-web
docker-compose down
```

docker-compose down会停stop容器并remove容器，很干净。如果要持久化数据，修改docker-compose.yml文件，做数据卷映射。

# 技术选型

