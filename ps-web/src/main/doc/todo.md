0.用户认证成功后的用户信息(角色，权限)，存到redis,顺手解决了分布式session的问题。似乎已经有了spring session功能：https://docs.spring.io/spring-session/docs/current/reference/html5/
对于用户认证，可以手写，也可以用shiro等框架。上面说的是认证成功后的事情。https://www.jianshu.com/p/cdf327a6a5a4  
1.捕获的全局异常，不仅打log到标准输出，也往es打一份，通过追加一个appender实现。  
2.前面部署nginx, load balance到post service  
3.post service、mapi、op三个service刚好试下微服务。  

4.搞个job，定期同步[endpoint，权限码集合]到数据库。spring-xxljob。

```java
@Controller
@ResponseBody
@Permission(value = PermissionConst.xxx)
public class PostSystemController {
    @Permission(value = PermissionConst.SHUTDOWN)
    @RequestMapping(value = "/shutdown", method = RequestMethod.GET)
    public void stop() {
    }
}
```

形如这样的话，数据库就应该有两条记录：

</,[ PermissionConst.xxx]>

<\/shutdown,[PermissionConst.xxx,PermissionConst.SHUTDOWN]>

如何拿到所有的controller类：检查所有bean，看有没有被controoler或restcontroller注解

Java 定时任务的几种实现方式：定期同步的用spring

- 基于 java.util.Timer 定时器，实现类似闹钟的定时任务
- 使用 Quartz、elastic-job、xxl-job 等开源第三方定时任务框架，适合分布式项目应用
- 使用 Spring 提供的一个注解： @Schedule，开发简单，使用比较方便，也是本文介绍的一种方式
  作者：武培轩
  链接：https://juejin.cn/post/6844903968292732941
  来源：掘金
  著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

5.swagger，接口太多了