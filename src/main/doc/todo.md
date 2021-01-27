0.用户认证成功后的用户信息(角色，权限)，存到redis,顺手解决了分布式session的问题。似乎已经有了spring session功能：https://docs.spring.io/spring-session/docs/current/reference/html5/
对于用户认证，可以手写，也可以用shiro等框架。上面说的是认证成功后的事情。https://www.jianshu.com/p/cdf327a6a5a4  
1.捕获的全局异常，不仅打log到标准输出，也往es打一份。  
2.前面部署nginx, load balance到post service  
3.post service、mapi、op三个service刚好试下微服务。  
